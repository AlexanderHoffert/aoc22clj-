(ns advent-of-code.day-01
  (:require [clojure.string :as string]))

(defn get-sums [food]
  (transduce (map #(Integer/parseInt %)) + 0 food))

(defn parse-input [input]
  (->> (string/split input #"\n *\n")
       (map string/split-lines)
       (map get-sums)
       (sort-by -)))

(defn part-1
  "Day 01 Part 1"
  [input]
  (->> input
       parse-input
       first))

(defn part-2
  "Day 01 Part 2"
  [input]
  (->> input
       parse-input
       (transduce (take 3) + 0)))
