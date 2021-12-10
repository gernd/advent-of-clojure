(ns clojureadv.2021.util
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn read-file-to-int-arr! [filename]
  (->> filename
       io/resource
       slurp
       str/split-lines
       (map #(Integer/parseInt %))))
