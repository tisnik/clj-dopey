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

(ns clj-dopey.irc-bot-test
  (:require [clojure.test :refer :all]
            [clj-dopey.irc-bot :refer :all]))

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

(deftest test-message-to-channel?-existence
    "Check that the clj-dopey.irc-bot/message-to-channel? definition exists."
    (testing "if the clj-dopey.irc-bot/message-to-channel? definition exists."
        (is (callable? 'clj-dopey.irc-bot/message-to-channel?))))


(deftest test-message-for-me?-existence
    "Check that the clj-dopey.irc-bot/message-for-me? definition exists."
    (testing "if the clj-dopey.irc-bot/message-for-me? definition exists."
        (is (callable? 'clj-dopey.irc-bot/message-for-me?))))


(deftest test-create-reply-existence
    "Check that the clj-dopey.irc-bot/create-reply definition exists."
    (testing "if the clj-dopey.irc-bot/create-reply definition exists."
        (is (callable? 'clj-dopey.irc-bot/create-reply))))


(deftest test-format-schedule-existence
    "Check that the clj-dopey.irc-bot/format-schedule definition exists."
    (testing "if the clj-dopey.irc-bot/format-schedule definition exists."
        (is (callable? 'clj-dopey.irc-bot/format-schedule))))


(deftest test-is-word-from-dictionary?-existence
    "Check that the clj-dopey.irc-bot/is-word-from-dictionary? definition exists."
    (testing "if the clj-dopey.irc-bot/is-word-from-dictionary? definition exists."
        (is (callable? 'clj-dopey.irc-bot/is-word-from-dictionary?))))


(deftest test-return-words-from-dictionary-existence
    "Check that the clj-dopey.irc-bot/return-words-from-dictionary definition exists."
    (testing "if the clj-dopey.irc-bot/return-words-from-dictionary definition exists."
        (is (callable? 'clj-dopey.irc-bot/return-words-from-dictionary))))


(deftest test-dictionary-status-existence
    "Check that the clj-dopey.irc-bot/dictionary-status definition exists."
    (testing "if the clj-dopey.irc-bot/dictionary-status definition exists."
        (is (callable? 'clj-dopey.irc-bot/dictionary-status))))


(deftest test-prepare-reply-text-existence
    "Check that the clj-dopey.irc-bot/prepare-reply-text definition exists."
    (testing "if the clj-dopey.irc-bot/prepare-reply-text definition exists."
        (is (callable? 'clj-dopey.irc-bot/prepare-reply-text))))


(deftest test-on-incoming-message-existence
    "Check that the clj-dopey.irc-bot/on-incoming-message definition exists."
    (testing "if the clj-dopey.irc-bot/on-incoming-message definition exists."
        (is (callable? 'clj-dopey.irc-bot/on-incoming-message))))


(deftest test-send-message-existence
    "Check that the clj-dopey.irc-bot/send-message definition exists."
    (testing "if the clj-dopey.irc-bot/send-message definition exists."
        (is (callable? 'clj-dopey.irc-bot/send-message))))


(deftest test-start-irc-bot-existence
    "Check that the clj-dopey.irc-bot/start-irc-bot definition exists."
    (testing "if the clj-dopey.irc-bot/start-irc-bot definition exists."
        (is (callable? 'clj-dopey.irc-bot/start-irc-bot))))

;
; Tests for various functions
;

(deftest test-message-to-channel?
    "Check the behaviour of function clj-dopey.irc-bot/message-to-channel?"
    (testing "the function message-to-channel?"
        (is (message-to-channel? {:target "#"}))
        (is (message-to-channel? {:target "#channel"}))
        (is (message-to-channel? {:target "#channel message"}))
        (is (not (message-to-channel? {:target ""})))
        (is (not (message-to-channel? {:target "channel"})))
        (is (not (message-to-channel? {:target "channel message"})))))

