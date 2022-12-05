(ns advent-of-code.day-04
  (:require [clojure.string :as string]))

(defn- parse-line [line]
  (->> line
       (re-seq #"[\d.]+")
       (map read-string)
       (zipmap [:s1 :e1 :s2 :e2])))

(defn- parse-input [input]
  (->> input
       string/split-lines
       (map parse-line)))

(defn- fully-contains [pair]
  (let [{s1 :s1
         s2 :s2
         e1 :e1
         e2 :e2} pair]
    (or (and (<= s1 s2) (>= e1 e2))
        (and (<= s2 s1) (>= e2 e1)))))

(defn- overlaps [pair]
  (let [{s1 :s1
         s2 :s2
         e1 :e1
         e2 :e2} pair]
    (or (and (<= s1 s2) (<= s2 s1))
        (and (<= s1 e2) (<= e2 e1))
        (and (<= s2 s1) (<= s1 e2))
        (and (<= s2 e1) (<= e1 e2)))))

(defn- get-result [input filter-function]
  (->> input
       parse-input
       (filter filter-function)
       count))

(defn part-1
  "Day 04 Part 1"
  [input]
  (get-result input fully-contains))

(defn part-2
  "Day 04 Part 2"
  [input]
  (get-result input overlaps))
