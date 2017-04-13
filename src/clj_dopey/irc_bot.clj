;
;  (C) Copyright 2017  Pavel Tisnovsky
;
;  All rights reserved. This program and the accompanying materials
;  are made available under the terms of the Eclipse Public License v1.0
;  which accompanies this distribution, and is available at
;  http://www.eclipse.org/legal/epl-v10.html
;
;  Contributors:
;      Pavel Tisnovsky
;

(ns clj-dopey.irc-bot)

(require '[irclj.core :as irc])

(require '[clj-dopey.core     :as core])
(require '[clj-dopey.dyncfg   :as dyncfg])
(require '[clj-dopey.dictionary :as dictionary])

(defn load-data-file
    [filename]
    (-> filename
        slurp
        clojure.string/split-lines))

(defn load-vocabulary
    [filename]
    (into [] (load-data-file filename)))

(defn message-to-channel?
    [message]
    (.startsWith (:target message) "#"))

(defn message-for-me?
    [my-name special-prefix message]
    (or (.startsWith (:target message) my-name)        ; private message
        (.startsWith (:text message) (str my-name ":")); direct message
        (.startsWith (:text message) (str my-name ",")); direct message
        (and (.startsWith (:text message) special-prefix)   ; special prefix
             (not (= (.trim (:text message)) special-prefix)))
    ))

(defn create-reply
    [incoming-message]
    (if (message-to-channel? incoming-message)
        incoming-message
        (assoc incoming-message :target (:nick incoming-message))))

(defn format-schedule
    [schedule]
    (apply str (for [s schedule]
       ;(str (subs (-> s :formatted :from) 0 5) "-" (subs (-> s :formatted :to) 0 5) ", "))))
       (str (subs (-> s :formatted :from) 0 5) "  "))))

(defn is-word-from-dictionary?
    [input]
    (dictionary/word-exist? input))

(defn return-words-from-dictionary
    [input]
    (dictionary/find-word input))

(defn dictionary-status
    []
    (str "Number of terms in dictionary: " (dictionary/term-count)))

(defn prepare-reply-text
    [incomming-message nick input-text]
    (try
    (let [in-channel? (message-to-channel? incomming-message)
          modules     (-> @dyncfg/configuration :modules)
          input       (if in-channel?
                          (if (.startsWith input-text (-> @dyncfg/configuration :bot :prefix))
                              (subs input-text 1)
                              (subs input-text (+ 2 (count @dyncfg/bot-nick))))
                          input-text)
          prefix      (if in-channel? (str nick ": "))
          response    (condp = input
                          "help"     (-> @dyncfg/configuration :bot :help)
                          "status"   (dictionary-status)
                          "rainbow"   (apply str (for [color (range 16)]
                                                     (str (char 3) (format "%02d" color) (format "test%02d " color) (char 3) "99")))
                          (if (is-word-from-dictionary? input)
                              (return-words-from-dictionary input)
                              "Command not understood or term not found"))]
        {:prefix prefix
         :response response})
        (catch Exception e
            (println (.getMessage e))
            {:prefix ""
             :response ""})))

(defn on-incoming-message
    [connection incoming-message]
    (let [{text    :text
           target  :target
           nick    :nick
           host    :host
           command :command} incoming-message]
           (println "Received message from" nick "to" target ":" text "(" host command ")")
           (println incoming-message)
           (if (message-for-me? @dyncfg/bot-nick (-> @dyncfg/configuration :bot :prefix) incoming-message)
               (let [reply  (create-reply incoming-message)
                     output (prepare-reply-text incoming-message nick text)]
                     (if (seq? (:response output))
                         (doseq [r (:response output)]
				 (irc/reply connection reply
					  (str (:prefix output) r)))
		         (irc/reply connection reply
				  (str (:prefix output) (:response output))
))))))

(defn send-message
    [recipients target message-text]
    (let [message {:target target :command "PRIVMSG"}]
        (irc/reply @dyncfg/connection message (str recipients " " message-text))))

(defn start-irc-bot
    [configuration]
    (let [server   (:name configuration)
          port     (:port configuration)
          channels (:channels configuration)
          chanlist (clojure.string/split channels #" ")
          nick     (:nick configuration)]
        (println "Connecting to" server "on port" port)
        (let [conn (irc/connect server port nick
                                :callbacks {:privmsg on-incoming-message})]
            (println "Connected, joining to channels" channels)
            (reset! dyncfg/connection conn)
            (reset! dyncfg/bot-nick nick)
            (doseq [channel chanlist]
                (println channel)
                (irc/join @dyncfg/connection (clojure.string/trim channel)))
            (println "Connected..."))))
