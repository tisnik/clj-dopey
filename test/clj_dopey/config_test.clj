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

;
; Test for function behaviours
;

(deftest test-parse-boolean
    "Check the behaviour of function emender-jenkins.config/parse-boolean."
    (are [x y] (= x y)
        true (parse-boolean "true")
        true (parse-boolean "True")
        false (parse-boolean "false")
        false (parse-boolean "False")
        false (parse-boolean "")
        false (parse-boolean "unknown")
        false (parse-boolean nil)))

(deftest test-parse-int-zero
    "Check the behaviour of function zg.config/parse-int."
    (are [x y] (== x y)
        0 (parse-int "0")
        0 (parse-int "00")
        0 (parse-int "000")
        0 (parse-int "-0")
        0 (parse-int "-00")
        0 (parse-int "-000")))

(deftest test-parse-int-positive-int
    "Check the behaviour of function zg.config/parse-int."
    (are [x y] (== x y)
        1          (parse-int "1")
        2          (parse-int "2")
        42         (parse-int "42")
        65535      (parse-int "65535")
        65536      (parse-int "65536")
        2147483646 (parse-int "2147483646")))

(deftest test-parse-int-negative-int
    "Check the behaviour of function zg.config/parse-int."
    (are [x y] (== x y)
        -1          (parse-int "-1")
        -2          (parse-int "-2")
        -42         (parse-int "-42")
        -65535      (parse-int "-65535")
        -65536      (parse-int "-65536")
        -2147483647 (parse-int "-2147483647")))

(deftest test-parse-int-min-int
    "Check the behaviour of function zg.config/parse-int."
    (is (== Integer/MIN_VALUE (parse-int "-2147483648"))))

(deftest test-parse-int-max-int
    "Check the behaviour of function zg.config/parse-int."
    (is (== Integer/MAX_VALUE (parse-int "2147483647"))))

(deftest test-parse-int-overflow
    "Check the behaviour of function zg.config/parse-int."
    (are [x] (thrown? NumberFormatException x)
        (parse-int "2147483648")
        (parse-int "-2147483649")))

(deftest test-parse-int-bad-input
    "Check the behaviour of function zg.config/parse-int."
    (are [x] (thrown? NumberFormatException x)
        (parse-int "")
        (parse-int " ")
        (parse-int "xyzzy")))
       ; (parse-int "+1"))) ; removed, not compatible with all supported JDKs

