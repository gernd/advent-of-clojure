(ns clojureadv.2021.day1
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.tools.trace :refer :all]
            [clojureadv.2021.util :as :util]
            ))

(def test-data [213 123 123 123 12 20 19 18 100 200 300 400])


(deftrace compute-sonar [history current]
          (let [prev (:prev history)
                result (:result history)]
            (cond
              (nil? prev) (assoc history :prev current :result (conj result :nil))
              (< prev current) (assoc history :prev current :result (conj result :increased))
              :else (assoc history :prev current :result (conj result :decreased))
              )))

(comment deftrace solve [data]
          (->> data
               (reduce compute-sonar {:prev nil :result (vector)})
               (:result)
               (filter #(= % :increased))
               count))
