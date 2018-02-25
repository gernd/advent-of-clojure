(defn get-index-of-highest-number
  [haystack]
  (.indexOf haystack (reduce #(if (> %1  %2) %1 %2) haystack)))

(defn redistribute-cells
  [memory index-of-last-update cells-to-distribute]
  (if (= 0 cells-to-distribute) 
    memory
  (let [index-of-next-update (mod (inc index-of-last-update) (count memory))
        updated-mem (assoc memory index-of-next-update (inc (get memory index-of-next-update)))]
     (recur updated-mem index-of-next-update (dec cells-to-distribute)))))



(defn solve-memory-reallocation
  [memory seen-configurations steps]
  ;(do (println "current mem " memory " seen configs " seen-configurations " steps " steps)
  (let [index-of-highest-number (get-index-of-highest-number memory)
        blocks-to-distribute (get memory index-of-highest-number)
        zeroed-memory (assoc memory index-of-highest-number 0)
        updated-memory (redistribute-cells zeroed-memory index-of-highest-number blocks-to-distribute)
        updated-steps (inc steps)]
  (if (some #(= updated-memory %) seen-configurations)
    updated-steps
  (recur updated-memory (conj seen-configurations updated-memory) updated-steps))))

(solve-memory-reallocation [4 1 15 12 0 9 9 5 5 8 7 3 14 5 12 3] [] 0)
