(ns advent-of-code.day-02
  (:require [clojure.string :as string]))

(def win-score {"A" {"X" 3, "Y" 6, "Z" 0}
                "B" {"X" 0, "Y" 3, "Z" 6}
                "C" {"X" 6, "Y" 0, "Z" 3}})

(def correct-choice {"A" {"X" "Z", "Y" "X", "Z" "Y"}
                     "B" {"X" "X", "Y" "Y", "Z" "Z"}
                     "C" {"X" "Y", "Y" "Z", "Z" "X"}})

(def choice-score {"X" 1, "Y" 2, "Z" 3})

(defn get-map-value [a-map round]
  (-> (get a-map (first round))
      (get (second round))))

(defn parse-input [input]
  (->> (string/split-lines input)
       (map #(string/split % #" "))))

(defn get-value [round]
  (+ (get choice-score (second round))
     (get-map-value win-score round)))

(defn get-score [rounds]
  (->> (map get-value rounds)
       (reduce +)))

(defn map-round [round]
  (list (first round) (get-map-value correct-choice round)))

(defn part-1
  "Day 02 Part 1"
  [input]
  (->> input
       parse-input
       get-score))

(defn part-2
  "Day 02 Part 2"
  [input]
  (->> input
       parse-input
       (map map-round)
       get-score))
