(ns advent-of-code.day-01-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.day-01 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (let [expected 24000]
    (is (= expected (part-1 (slurp (resource "day-01-example.txt")))))))

(deftest part2
  (let [expected 45000]
    (is (= expected (part-2 (slurp (resource "day-01-example.txt")))))))
