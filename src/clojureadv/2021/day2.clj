(ns clojureadv.2021.day2
  (:require [clojure.tools.trace :refer :all]
            [clojureadv.2021.util :as util]
            [clojure.string :as str]
            ))

(defonce day2-test-data (util/read-file-to-lines! "2021/day2-test.txt"))
(defonce day2-data (util/read-file-to-lines! "2021/day2.txt"))

(defn compute-drive [current-position command]
  (cond
    (= "forward" (:name command)) (update-in current-position [:x] + (:value command))
    (= "down" (:name command)) (update-in current-position [:y] + (:value command))
    (= "up" (:name command)) (update-in current-position [:y] - (:value command))
    )
  )

(defn build-command [line]
          (let [splitted-line (str/split line #"\s+")]
            {:name (first splitted-line) :value (Integer/parseInt (second splitted-line))
             }))

(defn solve-day2-first-part [lines]
  (let [ final-position
  (->> lines
       (map  build-command)
       (reduce compute-drive {:x 0 :y 0})
       ) ]
    (* (:x final-position) (:y final-position))))

(deftrace compute-drive-with-aim [current-status command]
  (cond
    (= "forward" (:name command)) (assoc current-status
                                    :x (+ (:x current-status) (:value command))
                                    :y (+ (:y current-status) (* (:aim current-status) (:value command))))

    (= "down" (:name command)) (update current-status :aim + (:value command))
    (= "up" (:name command)) (update current-status :aim - (:value command))
    )
  )

(defn solve-day2-second-part [lines]
  (let [ final-position
        (->> lines
             (map  build-command)
             (reduce compute-drive-with-aim {:x 0 :y 0 :aim 0})
             ) ]
    (* (:x final-position) (:y final-position))))
