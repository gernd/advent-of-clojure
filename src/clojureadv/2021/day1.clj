(ns clojureadv.2021.day1
  (:require [clojureadv.2021.util :as util]
            [clojure.tools.trace :refer :all]))

(def test-data [213 123 123 123 12 20 19 18 100 200 300 400])

(deftrace compute-sonar [history current]
  (let [prev (:prev history)
        result (:result history)]
    (cond
      (nil? prev) (assoc history :prev current :result (conj result :nil))
      (< prev current) (assoc history :prev current :result (conj result :increased))
      :else (assoc history :prev current :result (conj result :decreased)))))

(deftrace solve [data]
  (->> data
       (reduce compute-sonar {:prev nil :result (vector)})
       (:result)
       (filter #(= % :increased))
       count))

(deftrace compute-window-difference [vals]
  (let [reversed-vals (reverse vals)
        new-window (take 3 reversed-vals)
        previous-window (->> reversed-vals (take 4) (drop 1))]
    (if (> (apply + new-window) (apply + previous-window)) :increased
        :decreased)))

(defn compute-sliding-window [history current]
  (let [previous-vals (:vals history)
        new-vals (conj previous-vals current)
        sliding-window-computations (:computations history)]
    (cond
      (< (count new-vals) 3)
      ; not enough values to calculate a window
      (assoc history :vals new-vals)

      (= 3 (count new-vals))
      ; only one window filled
      (assoc history :vals new-vals :computations (conj sliding-window-computations nil))

      :else
      ; we have at least two sliding windows
      (assoc history :vals new-vals :computations (conj sliding-window-computations (compute-window-difference new-vals))))))

(defn solve-part-2 [data]
  (->> data
       (reduce compute-sliding-window {:computations (vector) :vals (vector)})
       (:computations)
       (filter #(= % :increased))
       count))
