(ns advent-of-code.day-06-test
  (:require [clojure.test :refer [deftest testing is]]
            [advent-of-code.day-06 :refer [part-1 part-2]]
            [clojure.java.io :refer [resource]]))

(deftest part1
  (let [expected 7]
    (is (= expected (part-1 (slurp (resource "day-06-example.txt")))))
    (is (= 5 (part-1 "bvwbjplbgvbhsrlpgdmjqwftvncz")))
    (is (= 6 (part-1 "nppdvjthqldpwncqszvftbrmjlhg")))
    (is (= 10 (part-1 "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")))
    (is (= 11 (part-1 "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")))))

(deftest part2
  (let [expected 19]
    (is (= expected (part-2 (slurp (resource "day-06-example.txt")))))
    (is (= 23 (part-2 "bvwbjplbgvbhsrlpgdmjqwftvncz")))
    (is (= 23 (part-2 "nppdvjthqldpwncqszvftbrmjlhg")))
    (is (= 29 (part-2 "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")))
    (is (= 26 (part-2 "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")))))
