(use 'clojure.java.io)

(defn is-pw-valid-help
[seen-pws remaining-pws]
(do (println "seen-pws " seen-pws " remaining-pws " remaining-pws)
(if (empty? remaining-pws)
  true
(if (some #(= (first remaining-pws) %) seen-pws)
  false
(is-pw-valid-help (conj seen-pws (first remaining-pws)) (rest remaining-pws))))))
  
(defn is-pw-valid
[pws]
(is-pw-valid-help [] pws))

(defn get-lines
      [fname]
      (with-open [r (reader fname)]
                (doall (line-seq r))))

(defn load-phrases 
[]
(let [lines (get-lines "day_4/passphrases.txt")]
  (map #(clojure.string/split % #" ") lines)))

(let [phrases (load-phrases)
      valid-phrases (map #(if (is-pw-valid %) 1 0) phrases)]
    (reduce + 0 valid-phrases))
