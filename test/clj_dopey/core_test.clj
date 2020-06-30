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

(ns clj-dopey.core-test
  (:require [clojure.test       :refer :all]
            [clj-dopey.core     :refer :all]
            [clj-dopey.importer :refer :all]))

;
; Common functions used by tests.
;

(defn callable?
    "Test if given function-name is bound to the real function."
    [function-name]
    (clojure.test/function? function-name))

;
; Tests for various functions
;

(ns clj-dopey.core-test
  (:require [clojure.test :refer :all]
            [clj-dopey.core :refer :all]))


(deftest test-start-bot-existence
    "Check that the clj-dopey.core/start-bot definition exists."
    (testing "if the clj-dopey.core/start-bot definition exists."
        (is (callable? 'clj-dopey.core/start-bot))))


(deftest test-show-help-existence
    "Check that the clj-dopey.core/show-help definition exists."
    (testing "if the clj-dopey.core/show-help definition exists."
        (is (callable? 'clj-dopey.core/show-help))))


(deftest test-run-app-existence
    "Check that the clj-dopey.core/run-app definition exists."
    (testing "if the clj-dopey.core/run-app definition exists."
        (is (callable? 'clj-dopey.core/run-app))))

(deftest test--main-existence
    "Check that the clj-dopey.core/-main definition exists."
    (testing "if the clj-dopey.core/-main definition exists."
        (is (callable? 'clj-dopey.core/-main))))

(deftest test-show-help
    "Check the function show-help"
    (testing "the function show-help"
       ; use mock instead of println function
        (with-redefs [println (fn [string] string)]
            (is (= "help"         (show-help "help")))
            (is (= "line1\nline2" (show-help "line1\nline2"))))))

(deftest test-run-app
    "Check the function run-app"
    (testing "the function run-app"
        (with-redefs [show-help   (fn [summary] :help)
                      import-data (fn [import?] :import)
                      start-bot   (fn [] :start-bot)]
            (is (= :help      (run-app "summary" true false)))
            (is (= :import    (run-app "summary" false true)))
            (is (= :start-bot (run-app "summary" false false))))))

