(ns advent-of-code.day-06)

(defn get-marker [input size]
  (let [windows (partition size 1 input)]
    (loop [position size
           current (first windows)
           rest (drop 1 windows)]
      (if (apply distinct? current)
        position
        (recur (+ 1 position) (first rest) (drop 1 rest))))))

(defn part-1
  "Day 06 Part 1"
  [input]
  (get-marker input 4))

(defn part-2
  "Day 06 Part 2"
  [input]
  (get-marker input 14))
