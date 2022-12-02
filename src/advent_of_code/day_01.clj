(ns advent-of-code.day-01)
(require '[clojure.string :as string])

(defn get-sums [food]
  (->> food
       (map #(Integer/parseInt %))
       (reduce +)))

(defn parse-input [input]
  (->> input
       string/split-lines
       (partition-by #(= "" %))
       (filter #(not (= "" (first %))))
       (map get-sums)
       (sort)))

(defn part-1
  "Day 01 Part 1"
  [input]
  (->> input
       parse-input
       last))

(defn part-2
  "Day 01 Part 2"
  [input]
  (->> input
       parse-input
       (take-last 3)
       (reduce +)))
