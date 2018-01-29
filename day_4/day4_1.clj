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

(is-pw-valid ["aa", "AA", "bb", "cc" "uu", "aa"])
