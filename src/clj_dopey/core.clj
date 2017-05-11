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

(ns clj-dopey.core
  (:gen-class))

(require '[clojure.pprint        :as pprint])

(require '[irclj.core            :as irc])

(require '[clojure.tools.cli     :as cli])
(require '[clojure.tools.logging :as log])

(require '[clj-dopey.config      :as config])
(require '[clj-dopey.irc-bot     :as irc-bot])
(require '[clj-dopey.dyncfg      :as dyncfg])
(require '[clj-dopey.importer    :as importer])

(def cli-options
    "Definitions of all command line options that are  currenty supported."
    ;; an option with a required argument
    [["-h"   "--help"                       "show help"                   :id :help]
     ["-i"   "--import datafile.csv"        "import data into dictionary" :id :import]])

(defn start-bot
    []
    (let [config (config/load-configuration "config.ini")]
         (reset! dyncfg/configuration config)
         (config/print-configuration config)
         (irc-bot/start-irc-bot (:server config))))

(defn show-help
    "Show help and all supported CLI flags."
    [summary]
    (println "Usage:")
    (println summary))

(defn -main
    "Entry point to this bot."
    [& args]
    (log/info "Starting the application")
    (let [all-options (cli/parse-opts args cli-options)
          options     (all-options :options)
          show-help?  (options :help)
          import?     (options :import)]
          (cond show-help? (show-help (:summary all-options))
                import?    (importer/import-data import?)
                :else      (start-bot)))
    (log/info "Exiting from the application"))

