(ns advent-of-code.day-03
  (:require [clojure.set :as set]
            [clojure.string :as string]))

(def a-value (int \a))
(def A-value (int \A))

(defn parse-line [line]
  (let [half (/ (count line) 2)]
    (vector
     (set (take half line))
     (set (drop half line)))))

(defn parse-input1 [input]
  (->> input
       string/split-lines
       (map parse-line)))

(defn parse-input2 [input]
  (->> input
       string/split-lines
       (partition 3)
       (map #(map set %))))

(defn get-priority [item]
  (let [value (int item)]
    (if (< value a-value)
      (+ 27 (- value A-value))
      (+ 1 (- value a-value)))))

(defn get-priority-of-rucksack [rucksack]
  (->> rucksack
       (#(apply set/intersection %))
       first
       get-priority))

(defn sum-priorities [rucksacks]
  (transduce (map get-priority-of-rucksack) + 0 rucksacks))

(defn part-1
  "Day 03 Part 1"
  [input]
  (->> input
       parse-input1
       sum-priorities))

(defn part-2
  "Day 03 Part 2"
  [input]
  (->> input
       parse-input2
       sum-priorities))

(comment
  (get-priority-of-rucksack [#{\a \b \c} #{\a \d}])
  (sum-priorities '([#{\a \b \c} #{\a \d}])))
