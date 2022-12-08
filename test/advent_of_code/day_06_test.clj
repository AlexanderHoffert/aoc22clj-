(ns advent-of-code.day-06-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.day-06 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part1-base
  (let [expected 7]
    (is (= expected (part-1 (slurp (resource "day-06-example.txt")))))))

(deftest part1-1
  (is (= 5 (part-1 "bvwbjplbgvbhsrlpgdmjqwftvncz"))))

(deftest part1-2
  (is (= 6 (part-1 "nppdvjthqldpwncqszvftbrmjlhg"))))

(deftest part1-3
  (is (= 10 (part-1 "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"))))

(deftest part1-4
  (is (= 11 (part-1 "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"))))

(deftest part2
  (let [expected 19]
    (is (= expected (part-2 (slurp (resource "day-06-example.txt")))))))
