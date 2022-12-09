(ns advent-of-code.day-07
  (:require [clojure.string :as string]))

(defn- add-lines [all-lines]
  (loop [fs {}
         [line & lines] all-lines
         path []]
    (if-not line
      fs
      (let [[_             ls      dir  size  file        cd]
            (re-find #"\$ (ls)|dir (.+)|(\d+) (.*)|\$ cd (.+)" line)]
        (cond
          ls (recur fs lines path)
          dir (recur (assoc-in fs (conj path dir) {}) lines path)
          file (recur (assoc-in fs (conj path file) (Integer/parseInt size)) lines path)
          cd (case cd
               "/" (recur fs lines [])
               ".." (recur fs lines (pop path))
               (recur fs lines (conj path cd))))))))

(defn- parse-input [input]
  (->> input
       string/split-lines
       add-lines))

(defn- get-sizes [fs]
  (let [result (new java.util.HashMap)
        collect (fn collect [path dir]
                  (let [size (transduce
                              (map (fn [[name size-or-dir]]
                                     (if (int? size-or-dir)
                                       size-or-dir
                                       (collect (conj path name) size-or-dir))))
                              +
                              0
                              dir)]
                    (. result put path size)
                    size))]
    (collect [] fs)
    (into {} result)))

(defn- sum-small-dirs [dirs]
  (transduce (comp (map val)
                   (filter #(< % 100000)))
             +
             0
             dirs))

(defn- get-smallest-viable [dirs]
  (let [sorted-dirs (sort-by val dirs)
        required-space (- (val (last sorted-dirs)) 40000000)]
    (->> sorted-dirs
         (map val)
         (filter #(< required-space %))
         first)))

(defn- get-result [input transformation]
  (->> input
       parse-input
       get-sizes
       transformation))

(defn part-1
  "Day 07 Part 1"
  [input]
  (get-result input sum-small-dirs))

(defn part-2
  "Day 07 Part 2"
  [input]
  (get-result input get-smallest-viable))
