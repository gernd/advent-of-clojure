(ns clojureadv.2021.util
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn read-file-to-lines! [filename]
  "Reads the files and returns its contents as array of strings. One string corresponds to one line of the file"
  (->> filename
       io/resource
       slurp
       str/split-lines))

(defn read-file-to-int-arr! [filename]
  (->> read-file-to-lines!
       (map #(Integer/parseInt %))))
