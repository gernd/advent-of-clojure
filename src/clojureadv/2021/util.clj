(ns clojureadv.2021.util)

(defn read-file-to-int-arr! [filename]
  (->> filename
       io/resource
       slurp
       str/split-lines
       (map #(Integer/parseInt %))))
