(use 'clojure.java.io)

(defn get-lines 
    [fname]
    (with-open [r (reader fname)]
        (doall (line-seq r))))

(defn get-int-lists
    "Expects a seq of string arrays and converts them to a vector of int vectors"
    [string-arrays]
        (loop [remaining-string-arrays string-arrays converted-arrays []]
            (if (empty? remaining-string-arrays) 
                converted-arrays
                (do (println "Remaining string arrays" remaining-string-arrays "converted arrays" converted-arrays)
                (let [[current-string-array & remaining] remaining-string-arrays](
                    recur remaining 
                        (into converted-arrays 
                            [(map (fn [a] (read-string a)) current-string-array)])))))))


(defn load-spreadsheet
    "Loads the spreadsheet from the file as a sequence of sequence of numbers"
    [file-name]
    (let [lines-in-file (get-lines file-name)
        lists-of-char-lists (map (fn [line] (clojure.string/split line #" ")) lines-in-file)]
        (get-int-lists lists-of-char-lists)))

(defn solve-spreadsheet
    [file-name]
    (let [spreadsheet (load-spreadsheet file-name)
        min-max-differences (map (fn [row] (- (apply max row) (apply min row))) spreadsheet)]
        (reduce + min-max-differences)))

(solve-spreadsheet "day_2/spreadsheet_2_1")
