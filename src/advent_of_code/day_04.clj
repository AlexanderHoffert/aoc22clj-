(ns advent-of-code.day-04
  (:require [clojure.string :as string]))

(defn parse-line [line]
  (->> line
       (re-seq #"[\d.]+")
       (map read-string)
       (zipmap [:s1 :e1 :s2 :e2])))

(defn parse-input [input]
  (->> input
       string/split-lines
       (map parse-line)))

(defn fully-contains [pair]
  (let [{s1 :s1
         s2 :s2
         e1 :e1
         e2 :e2} pair]
    (or (and (<= s1 s2) (>= e1 e2))
        (and (<= s2 s1) (>= e2 e1)))))

(defn overlaps [pair]
  (let [{s1 :s1
         s2 :s2
         e1 :e1
         e2 :e2} pair]
    (or (and (<= s1 s2) (<= s2 s1))
        (and (<= s1 e2) (<= e2 e1))
        (and (<= s2 s1) (<= s1 e2))
        (and (<= s2 e1) (<= e1 e2)))))

(defn part-1
  "Day 04 Part 1"
  [input]
  (->> input
       parse-input
       (filter fully-contains)
       count))

(defn part-2
  "Day 04 Part 2"
  [input]
  (->> input
       parse-input
       (filter overlaps)
       count))

(comment
  (overlaps (parse-line "1-2,2-3"))
  (part-1 "1-3,1-4
1-3,2-3
1-1,1-1
2-4,1-5
1-2,1-3
1-3,1-2
2-3,2-4"))
