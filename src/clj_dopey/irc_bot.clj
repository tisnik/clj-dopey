;
;  (C) Copyright 2017, 2020  Pavel Tisnovsky
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

(require '[clojure.tools.logging :as log])

(require '[clj-dopey.dyncfg      :as dyncfg])
(require '[clj-dopey.dictionary  :as dictionary])

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

(defn is-word-from-dictionary?
    [input]
    (dictionary/word-exist? input))

(defn return-words-from-dictionary
    [input]
    (dictionary/find-word input))

(defn use-wildchars?
    [input]
    (or (.startsWith input "*")
        (.endsWith input "*")))

(defn one-word-like-this?
    [input]
    (if (use-wildchars? input)
        (= (dictionary/words-like-this input) 1)))

(defn more-words-like-this?
    [input]
    (if (use-wildchars? input)
        (> (dictionary/words-like-this input) 1)))

(defn return-word-like-this
    [input]
    (dictionary/find-word-like-this input))

(defn return-more-words-like-this
    [input]
    (dictionary/find-words-like-this input))

(defn dictionary-status
    []
    (str "Number of terms in dictionary: " (dictionary/term-count)))

(defn get-input
    [in-channel? input-text]
    (if in-channel?
        (if (.startsWith input-text (-> @dyncfg/configuration :bot :prefix))
            (subs input-text 1)
            (subs input-text (+ 2 (count @dyncfg/bot-nick))))
        (if (.startsWith input-text "?")
            (subs input-text 1)
            input-text)))

(defn get-prefix
    [in-channel? nick]
    (if in-channel? (str nick ": ")))

(defn get-rainbow-as-message
    []
    (apply str (for [color (range 16)]
        (str (char 3) (format "%02d" color) (format "test%02d " color) (char 3) "99"))))

(defn prepare-reply-text
    [incomming-message nick input-text]
    (try
    (let [in-channel? (message-to-channel? incomming-message)
          input       (get-input in-channel? input-text)
          prefix      (get-prefix in-channel? nick)
          response    (condp = input
                          "help"     (-> @dyncfg/configuration :bot :help)
                          "status"   (dictionary-status)
                          "rainbow"  (get-rainbow-as-message)
                          (cond (is-word-from-dictionary? input) (return-words-from-dictionary input)
                                (one-word-like-this? input)      (return-word-like-this input)
                                (more-words-like-this? input)    (return-more-words-like-this input)
                                :else                            "Command not understood or term not found"))]
        {:prefix prefix
         :response response})
        (catch Exception e
            (log/error e "prepare-reply-text")
            {:prefix ""
             :response ""})))

(defn send-reply
    [connection reply output response]
    (irc/reply connection reply (str (:prefix output) response)))

(defn reply-to-incoming-message
    [connection text target nick host command incoming-message]
    (let [reply  (create-reply incoming-message)
          output (prepare-reply-text incoming-message nick text)]
          (if (seq? (:response output))
              (doseq [r (:response output)]
                 (send-reply connection reply output r))
              (send-reply connection reply output (:response output)))))

(defn on-incoming-message
    [connection incoming-message]
    (let [{text    :text
           target  :target
           nick    :nick
           host    :host
           command :command} incoming-message]
           (log/info (str "Received message from" nick "to" target ":" text "(" host command ")"))
           (log/info incoming-message)
           (if (message-for-me? @dyncfg/bot-nick (-> @dyncfg/configuration :bot :prefix) incoming-message)
               (reply-to-incoming-message connection text target nick host command incoming-message))))

(defn start-irc-bot
    [configuration]
    (let [server   (:name configuration)
          port     (:port configuration)
          channels (:channels configuration)
          chanlist (clojure.string/split channels #" ")
          nick     (:nick configuration)]
        (log/info "Connecting to" server "on port" port)
        (let [conn (irc/connect server port nick
                                :callbacks {:privmsg on-incoming-message})]
            (log/info "Connected, joining to channels" channels)
            (reset! dyncfg/connection conn)
            (reset! dyncfg/bot-nick nick)
            (doseq [channel chanlist]
                (log/info channel)
                (irc/join @dyncfg/connection (clojure.string/trim channel)))
            (log/info "Connected..."))))

