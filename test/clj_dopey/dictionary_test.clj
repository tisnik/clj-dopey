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

(ns clj-dopey.dictionary-test
  (:require [clojure.test :refer :all]
            [clj-dopey.dictionary :refer :all]
            [clj-dopey.db-interface :refer :all]))

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

(deftest test-yes-no-with-caution
  "Checks the function yes-no-with-caution"
  (testing "the function yes-no-with-caution"
           (is (= (yes-no-with-caution {:no 0, :yes 1, :with-caution 2} :no)
                  (red "no")))
           (is (= (yes-no-with-caution {:no 0, :yes 1, :with-caution 2} :yes)
                  (green "yes")))
           (is (= (yes-no-with-caution {:no 0, :yes 1, :with-caution 2}
                                       :with-caution)
                  (yellow "with caution")))
           (is (= (yes-no-with-caution {} :anything)
                  (red "error - word usage is not set!")))))

(deftest test-print-field
  "Checks the function print-field"
  (testing "the function print-field"
           (is (= (str (bold "title: ") " test "))
               (print-field "title" {:term "term"} :term))))

(deftest test-use-it
  "Checks the function use-it"
  (testing "the function use-it"
           (is (= java.lang.String (type (use-it {:term "term", :use 0}))))
           (is (= java.lang.String (type (use-it {:term "term", :use 1}))))
           (is (= java.lang.String (type (use-it {:term "term", :use 2}))))))

(deftest test-incorrect-forms-return-value
  "Checks the function incorrect-forms"
  (testing "the function incorrect-forms"
           (let [term {:class "class",
                       :use 1,
                       :description "description",
                       :source "source",
                       :correct-forms "correct-forms",
                       :incorrect-forms "incorrect-forms",
                       :see-also "see also",
                       :product "product"}]
             (is (= java.lang.String (type (incorrect-forms term)))))))

(deftest test-correct-forms-return-value
  "Checks the function correct-forms"
  (testing "the function correct-forms"
           (let [term {:class "class",
                       :use 1,
                       :description "description",
                       :source "source",
                       :correct-forms "correct-forms",
                       :incorrect-forms "incorrect-forms",
                       :see-also "see also",
                       :product "product"}]
             (is (= java.lang.String (type (correct-forms term)))))))

(deftest test-preferred-forms-return-value
  "Checks the function preferred-forms"
  (testing "the function preferred-forms"
           (let [term {:class "class",
                       :use 1,
                       :description "description",
                       :source "source",
                       :correct-forms "correct-forms",
                       :incorrect-forms "incorrect-forms",
                       :see-also "see also",
                       :product "product"}]
             (is (= java.lang.String (type (preferred-forms term)))))))

(deftest test-unknown-forms-return-value
  "Checks the function unknown-forms"
  (testing "the function unknown-forms"
           (let [term {:class "class",
                       :use 42,
                       :description "description",
                       :source "source",
                       :correct-forms "correct-forms",
                       :incorrect-forms "incorrect-forms",
                       :see-also "see also",
                       :product "product"}]
             (is (= java.lang.String (type (unknown-forms term)))))))

(deftest test-find-word
  "Checks the function find-word"
  (testing "the function find-word"
           (let [term1 {:class "class",
                        :use 0,
                        :description "description",
                        :source "source",
                        :correct-forms "correct-forms",
                        :incorrect-forms "incorrect-forms",
                        :see-also "see also",
                        :product "product"}
                 term2 {:class "class",
                        :use 1,
                        :description "description",
                        :source "source",
                        :correct-forms "correct-forms",
                        :incorrect-forms "incorrect-forms",
                        :see-also "see also",
                        :product "product"}
                 term3 {:class "class",
                        :use 2,
                        :description "description",
                        :source "source",
                        :correct-forms "correct-forms",
                        :incorrect-forms "incorrect-forms",
                        :see-also "see also",
                        :product "product"}
                 term4 {:class "class",
                        :use 42,
                        :description "description",
                        :source "source",
                        :correct-forms "correct-forms",
                        :incorrect-forms "incorrect-forms",
                        :see-also "see also",
                        :product "product"}]
             (with-redefs [select-words (fn [word use-like] [term1])]
               (is (= (first (find-word "word")) (correct-forms term1))))
             (with-redefs [select-words (fn [word use-like] [term2])]
               (is (= (first (find-word "word")) (incorrect-forms term2))))
             (with-redefs [select-words (fn [word use-like] [term3])]
               (is (= (first (find-word "word")) (preferred-forms term3))))
             (with-redefs [select-words (fn [word use-like] [term4])]
               (is (= (first (find-word "word")) (unknown-forms term4)))))))

(deftest test-find-word-like-this
  "Checks the function find-word-like-this"
  (testing
    "the function find-word-like-this"
    (let [term1 {:class "class",
                 :use 0,
                 :description "description",
                 :source "source",
                 :correct-forms "correct-forms",
                 :incorrect-forms "incorrect-forms",
                 :see-also "see also",
                 :product "product"}
          term2 {:class "class",
                 :use 1,
                 :description "description",
                 :source "source",
                 :correct-forms "correct-forms",
                 :incorrect-forms "incorrect-forms",
                 :see-also "see also",
                 :product "product"}
          term3 {:class "class",
                 :use 2,
                 :description "description",
                 :source "source",
                 :correct-forms "correct-forms",
                 :incorrect-forms "incorrect-forms",
                 :see-also "see also",
                 :product "product"}
          term4 {:class "class",
                 :use 42,
                 :description "description",
                 :source "source",
                 :correct-forms "correct-forms",
                 :incorrect-forms "incorrect-forms",
                 :see-also "see also",
                 :product "product"}]
      (with-redefs [select-words (fn [word use-like] [term1])]
        (is (= (first (find-word-like-this "word")) (correct-forms term1))))
      (with-redefs [select-words (fn [word use-like] [term2])]
        (is (= (first (find-word-like-this "word")) (incorrect-forms term2))))
      (with-redefs [select-words (fn [word use-like] [term3])]
        (is (= (first (find-word-like-this "word")) (preferred-forms term3))))
      (with-redefs [select-words (fn [word use-like] [term4])]
        (is (= (first (find-word-like-this "word")) (unknown-forms term4)))))))

