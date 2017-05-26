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

(ns clj-dopey.dictionary-test
  (:require [clojure.test :refer :all]
            [clj-dopey.dictionary :refer :all]))

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

(deftest test-highlight-existence
    "Check that the clj-dopey.dictionary/highlight definition exists."
    (testing "if the clj-dopey.dictionary/highlight definition exists."
        (is (callable? 'clj-dopey.dictionary/highlight))))


(deftest test-black-existence
    "Check that the clj-dopey.dictionary/black definition exists."
    (testing "if the clj-dopey.dictionary/black definition exists."
        (is (callable? 'clj-dopey.dictionary/black))))


(deftest test-red-existence
    "Check that the clj-dopey.dictionary/red definition exists."
    (testing "if the clj-dopey.dictionary/red definition exists."
        (is (callable? 'clj-dopey.dictionary/red))))


(deftest test-green-existence
    "Check that the clj-dopey.dictionary/green definition exists."
    (testing "if the clj-dopey.dictionary/green definition exists."
        (is (callable? 'clj-dopey.dictionary/green))))


(deftest test-blue-existence
    "Check that the clj-dopey.dictionary/blue definition exists."
    (testing "if the clj-dopey.dictionary/blue definition exists."
        (is (callable? 'clj-dopey.dictionary/blue))))


(deftest test-purple-existence
    "Check that the clj-dopey.dictionary/purple definition exists."
    (testing "if the clj-dopey.dictionary/purple definition exists."
        (is (callable? 'clj-dopey.dictionary/purple))))


(deftest test-yellow-existence
    "Check that the clj-dopey.dictionary/yellow definition exists."
    (testing "if the clj-dopey.dictionary/yellow definition exists."
        (is (callable? 'clj-dopey.dictionary/yellow))))


(deftest test-bold-existence
    "Check that the clj-dopey.dictionary/bold definition exists."
    (testing "if the clj-dopey.dictionary/bold definition exists."
        (is (callable? 'clj-dopey.dictionary/bold))))


(deftest test-yes-no-with-caution-existence
    "Check that the clj-dopey.dictionary/yes-no-with-caution definition exists."
    (testing "if the clj-dopey.dictionary/yes-no-with-caution definition exists."
        (is (callable? 'clj-dopey.dictionary/yes-no-with-caution))))


(deftest test-print-field-existence
    "Check that the clj-dopey.dictionary/print-field definition exists."
    (testing "if the clj-dopey.dictionary/print-field definition exists."
        (is (callable? 'clj-dopey.dictionary/print-field))))


(deftest test-use-it-existence
    "Check that the clj-dopey.dictionary/use-it definition exists."
    (testing "if the clj-dopey.dictionary/use-it definition exists."
        (is (callable? 'clj-dopey.dictionary/use-it))))


(deftest test-incorrect-forms-existence
    "Check that the clj-dopey.dictionary/incorrect-forms definition exists."
    (testing "if the clj-dopey.dictionary/incorrect-forms definition exists."
        (is (callable? 'clj-dopey.dictionary/incorrect-forms))))


(deftest test-correct-forms-existence
    "Check that the clj-dopey.dictionary/correct-forms definition exists."
    (testing "if the clj-dopey.dictionary/correct-forms definition exists."
        (is (callable? 'clj-dopey.dictionary/correct-forms))))


(deftest test-preferred-forms-existence
    "Check that the clj-dopey.dictionary/preferred-forms definition exists."
    (testing "if the clj-dopey.dictionary/preferred-forms definition exists."
        (is (callable? 'clj-dopey.dictionary/preferred-forms))))


(deftest test-find-word-existence
    "Check that the clj-dopey.dictionary/find-word definition exists."
    (testing "if the clj-dopey.dictionary/find-word definition exists."
        (is (callable? 'clj-dopey.dictionary/find-word))))


(deftest test-term-count-existence
    "Check that the clj-dopey.dictionary/term-count definition exists."
    (testing "if the clj-dopey.dictionary/term-count definition exists."
        (is (callable? 'clj-dopey.dictionary/term-count))))


(deftest test-word-exist?-existence
    "Check that the clj-dopey.dictionary/word-exist? definition exists."
    (testing "if the clj-dopey.dictionary/word-exist? definition exists."
        (is (callable? 'clj-dopey.dictionary/word-exist?))))

(deftest test-highlight
    "Checks the function highlight."
    (testing "the function highlight."
        (is (= java.lang.String (type (highlight "string" "code"))))
        (is (= (char 3) (.charAt (highlight "string" "code") 0)))))

(deftest test-black
    "Checks the function black."
    (testing "the function black."
        (is (= java.lang.String (type (black "string"))))
        (is (= (subs (black "string") 3 9) "string"))
        (is (= (subs (black "string") 1 3) "01"))))

(deftest test-red
    "Checks the function red."
    (testing "the function red."
        (is (= java.lang.String (type (red "string"))))
        (is (= (subs (red "string") 3 9) "string"))
        (is (= (subs (red "string") 1 3) "04"))))

(deftest test-green
    "Checks the function green."
    (testing "the function green."
        (is (= java.lang.String (type (green "string"))))
        (is (= (subs (green "string") 3 9) "string"))
        (is (= (subs (green "string") 1 3) "03"))))

(deftest test-blue
    "Checks the function blue."
    (testing "the function blue."
        (is (= java.lang.String (type (blue "string"))))
        (is (= (subs (blue "string") 3 9) "string"))
        (is (= (subs (blue "string") 1 3) "02"))))

(deftest test-purple
    "Checks the function purple."
    (testing "the function purple."
        (is (= java.lang.String (type (purple "string"))))
        (is (= (subs (purple "string") 3 9) "string"))
        (is (= (subs (purple "string") 1 3) "06"))))

(deftest test-yellow
    "Checks the function yellow."
    (testing "the function yellow."
        (is (= java.lang.String (type (yellow "string"))))
        (is (= (subs (yellow "string") 3 9) "string"))
        (is (= (subs (yellow "string") 1 3) "08"))))

(deftest test-bold
    "Checks the function bold."
    (testing "the function bold."
        (is (= java.lang.String (type (bold "string"))))
        (is (= (subs (bold "string") 1 7) "string"))))


