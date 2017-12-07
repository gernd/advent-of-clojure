(use 'clojure.java.io)

(defn get-numbers-from-lines
    [lines]
    (map (fn [line] (clojure.string/split line #" ")) lines))


(defn get-lines 
    [fname]
    (with-open [r (reader fname)]
        (doall (line-seq r))))

(get-numbers-from-lines
    (get-lines "day_2/test_spreadsheet"))
