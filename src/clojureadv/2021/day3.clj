(ns clojureadv.2021.day3
  (:require [clojure.tools.trace :refer :all]
            [clojureadv.2021.util :as util]
            [clojure.string :as str]))

(defonce day3-test-data (util/read-file-to-lines! "2021/day3-test.txt"))
(defonce day3-data (util/read-file-to-lines! "2021/day3.txt"))

(deftrace map-function-to-vals [f m]
  (into {} (for [[k v] m] [k (f v)])))

(deftrace compute-rate-bits [occurences]
  (->> occurences
       (map #(hash-map
              :gamma-bit (-> % second second first)
              :epsilon-bit (-> % second first first)))))

(deftrace compute-rates [rate-bits]
  (let [epsilon-rate-binary (->> rate-bits (reduce #(str %1 (:epsilon-bit %2)) ""))
        gamma-rate-binary (reduce #(str %1 (:gamma-bit %2)) "" rate-bits)]
    (trace epsilon-rate-binary)
    {:epsilon-rate (Integer/parseInt epsilon-rate-binary 2)
     :gamma-rate (Integer/parseInt gamma-rate-binary 2)}))

(deftrace compute-digit-frequencies [binary-string]
  (->> binary-string
       frequencies
       (sort-by val)))

(deftrace compute-occurences [binary-strings]
  (->> binary-strings
       (map-function-to-vals compute-digit-frequencies)
       (sort-by key)))

(deftrace index-to-digit [number]
  (->> number
       (map-indexed #(hash-map %1 %2))))

(deftrace compute-solution [rates]
  (* (:epsilon-rate rates) (:gamma-rate rates)))

(deftrace solve-day3-part-1 [data]
  (->> data
       (map index-to-digit)
       flatten
       (apply merge-with str)
       compute-occurences
       compute-rate-bits
       compute-rates
       compute-solution))