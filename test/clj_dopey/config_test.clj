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

(ns clj-dopey.config-test
  (:require [clojure.test :refer :all]
            [clj-dopey.config :refer :all]
            [clojure.pprint :as pprint]))

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
  (testing
    "if the clj-dopey.config/update-server-configuration definition exists."
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
  "Check the behaviour of function clj-dopey.config/parse-boolean."
  (are [x y]
       (= x y)
       true  (parse-boolean "true")
       true  (parse-boolean "True")
       false (parse-boolean "false")
       false (parse-boolean "False")
       false (parse-boolean "")
       false (parse-boolean "unknown")
       false (parse-boolean nil)))

(deftest test-parse-int-zero
  "Check the behaviour of function clj-dopey.config/parse-int."
  (are [x y]
       (== x y)
       0 (parse-int "0")
       0 (parse-int "00")
       0 (parse-int "000")
       0 (parse-int "-0")
       0 (parse-int "-00")
       0 (parse-int "-000")))

(deftest test-parse-int-positive-int
  "Check the behaviour of function clj-dopey.config/parse-int."
  (are [x y]
       (== x y)
       1          (parse-int "1")
       2          (parse-int "2")
       42         (parse-int "42")
       65535      (parse-int "65535")
       65536      (parse-int "65536")
       2147483646 (parse-int "2147483646")))

(deftest test-parse-int-negative-int
  "Check the behaviour of function clj-dopey.config/parse-int."
  (are [x y]
       (== x y)
       -1          (parse-int "-1")
       -2          (parse-int "-2")
       -42         (parse-int "-42")
       -65535      (parse-int "-65535")
       -65536      (parse-int "-65536")
       -2147483647 (parse-int "-2147483647")))

(deftest test-parse-int-min-int
  "Check the behaviour of function clj-dopey.config/parse-int."
  (is (== Integer/MIN_VALUE (parse-int "-2147483648"))))

(deftest test-parse-int-max-int
  "Check the behaviour of function clj-dopey.config/parse-int."
  (is (== Integer/MAX_VALUE (parse-int "2147483647"))))

(deftest test-parse-int-overflow
  "Check the behaviour of function clj-dopey.config/parse-int."
  (are [x]
       (thrown? NumberFormatException x)
       (parse-int "2147483648")
       (parse-int "-2147483649")))

(deftest test-parse-int-bad-input
  "Check the behaviour of function clj-dopey.config/parse-int."
  (are [x]
       (thrown? NumberFormatException x)
       (parse-int "")
       (parse-int " ")
       (parse-int "xyzzy")))
; (parse-int "+1"))) ; removed, not compatible with all supported JDKs

(deftest test-parse-float-zero
  "Check the behaviour of function clj-dopey.config/parse-float."
  (are [x y]
       (== x y)
       0.0 (parse-float "0")
       0.0 (parse-float "00")
       0.0 (parse-float "000")
       0.0 (parse-float "-0")
       0.0 (parse-float "-00")
       0.0 (parse-float "-000")))

(deftest test-parse-float-positive-values
  "Check the behaviour of function clj-dopey.config/parse-float."
  (are [x y]
       (== x y)
       0.5 (parse-float "0.5")
       1.0 (parse-float "1.0")
       1.5 (parse-float "1.5")
       2.0 (parse-float "2")
       1000.0 (parse-float "1000")
       10000.0 (parse-float "10000")
       1e10 (parse-float "10000000000")
       1e10 (parse-float "1e10")))

(deftest test-parse-float-negative-values
  "Check the behaviour of function clj-dopey.config/parse-float."
  (are [x y]
       (== x y)
       -0.5 (parse-float "-0.5")
       -1.0 (parse-float "-1.0")
       -1.5 (parse-float "-1.5")
       -2.0 (parse-float "-2")
       -1000.0 (parse-float "-1000")
       -10000.0 (parse-float "-10000")
       -1e10 (parse-float "-10000000000")
       -1e10 (parse-float "-1e10")))

(deftest test-parse-float-min-value
  "Check the behaviour of function clj-dopey.config/parse-float."
  (is (== Float/MIN_VALUE (parse-float "0x0.000002P-126f"))))

(deftest test-parse-float-max-value
  "Check the behaviour of function clj-dopey.config/parse-float."
  (is (== Float/MAX_VALUE (parse-float "0x1.fffffeP+127f"))))

(deftest test-parse-float-bad-input
  "Check the behaviour of function clj-dopey.config/parse-float."
  (are [x]
       (thrown? NumberFormatException x)
       (parse-float "")
       (parse-float "xyzzy")
       (parse-float "-1xyzzy")))

(deftest test-print-configuration
  "Check the behaviour of function clj-dopey.config/print-configuration."
  ; use mock instead of clojure.pprint/pprint
  (with-redefs [pprint/pprint (fn [configuration] (str configuration))]
    (is (not (nil? (print-configuration {:first 1, :second 2}))))
    (is (= (type (print-configuration {:first 1, :second 2}))
           java.lang.String))))

(deftest test-load-configuration-1
  "Check the behaviour of function clj-dopey.config/load-configuration."
  (let [cfg (load-configuration "test/test1.ini")] (is (not (nil? cfg)))))

(deftest test-load-configuration-2
  "Check the behaviour of function clj-dopey.config/load-configuration."
  (let [cfg (load-configuration "test/test1.ini")]
    (is (not (nil? (:server cfg))))
    (is (not (nil? (:bot cfg))))
    (is (nil? (:other cfg)))))

(deftest test-load-configuration-3
  "Check the behaviour of function clj-dopey.config/load-configuration."
  (let [cfg (load-configuration "test/test1.ini")]
    (are [x y] (= x y)
         (-> cfg :server :name)     "test.com"
         (-> cfg :server :port)     6667
         (-> cfg :server :channels) "#botwar"
         (-> cfg :server :nick)     "clj-dopey"
         (-> cfg :server :other)    nil)))

(deftest test-load-configuration-4
  "Check the behaviour of function clj-dopey.config/load-configuration."
  (let [cfg (load-configuration "test/test1.ini")]
    (are [x y] (= x y)
         (-> cfg :bot :prefix)     "?")))

