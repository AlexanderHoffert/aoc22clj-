(ns advent-of-code.day-05
  (:require [clojure.string :as string]))

(defn get-stacks [lines]
  (->> lines
       (map #(re-seq #"(?:\[(\w)\])|(?:\s\s\s(?:\s|$))" %))
       (filter not-empty)
       (map #(map second %))
       (apply (partial map vector)) ;; transpose
       (mapv #(filter not-empty %))))

(defn get-moves [lines]
  (->> lines
       (map #(re-find #"move (\d+) from (\d) to (\d)" %))
       (filter not-empty)
       (map (fn [line]
              (->> line
                   (map read-string)
                   (#(zipmap '(:amount :from :to) (drop 1 %)))
                   (#(assoc % :from (- (:from %) 1)))
                   (#(assoc % :to (- (:to %) 1))))))))

(defn parse-input [input]
  (->> input
       string/split-lines
       (#(hash-map :stacks (get-stacks %)
                   :procedure (get-moves %)))))

(defn rearrange [one-by-one stacks instruction]
  (let [move (->> stacks
                  (#(get % (:from instruction)))
                  (take (:amount instruction))
                  (#(if one-by-one (reverse %) %)))]
    (-> stacks
        (assoc (:from instruction) (-> stacks
                                       (get (:from instruction))
                                       (#(drop (:amount instruction) %))))
        (assoc (:to instruction) (-> stacks
                                     (get (:to instruction))
                                     (#(concat move %)))))))

(defn rearrange-all [one-by-one input]
  (reduce (partial rearrange one-by-one) (:stacks input) (:procedure input)))

(defn part-1
  "Day 05 Part 1"
  [input]
  (->> input
       parse-input
       (rearrange-all true)
       (map first)
       string/join))

(defn part-2
  "Day 05 Part 2"
  [input]
  (->> input
       parse-input
       (rearrange-all false)
       (map first)
       string/join))