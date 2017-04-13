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

(ns clj-dopey.config-test
  (:require [clojure.test :refer :all]
            [clj-dopey.config :refer :all]))

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

(deftest test-parse-int-existence
    "Check that the clj-dopey.config/parse-int definition exists."
    (testing "if the clj-dopey.config/parse-int definition exists."
        (is (callable? 'clj-dopey.config/parse-int))))


(deftest test-parse-float-existence
    "Check that the clj-dopey.config/parse-float definition exists."
    (testing "if the clj-dopey.config/parse-float definition exists."
        (is (callable? 'clj-dopey.config/parse-float))))


(deftest test-parse-boolean-existence
    "Check that the clj-dopey.config/parse-boolean definition exists."
    (testing "if the clj-dopey.config/parse-boolean definition exists."
        (is (callable? 'clj-dopey.config/parse-boolean))))


(deftest test-update-server-configuration-existence
    "Check that the clj-dopey.config/update-server-configuration definition exists."
    (testing "if the clj-dopey.config/update-server-configuration definition exists."
        (is (callable? 'clj-dopey.config/update-server-configuration))))


(deftest test-load-configuration-existence
    "Check that the clj-dopey.config/load-configuration definition exists."
    (testing "if the clj-dopey.config/load-configuration definition exists."
        (is (callable? 'clj-dopey.config/load-configuration))))


(deftest test-print-configuration-existence
    "Check that the clj-dopey.config/print-configuration definition exists."
    (testing "if the clj-dopey.config/print-configuration definition exists."
        (is (callable? 'clj-dopey.config/print-configuration))))

