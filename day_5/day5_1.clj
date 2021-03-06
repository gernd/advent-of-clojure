(use 'clojure.java.io)

(defn calculate-steps 
[steps-done ip instructions]
  (do (println "steps-done " steps-done " instruction pointer " ip)
    (if (or (< ip 0)(>= ip (count instructions)))
      steps-done 
    (let  [offset (get instructions ip)
          new-ip (+ ip offset)
          updated-offset (inc offset)
          updated-instructions (assoc instructions ip updated-offset)]
        (recur (inc steps-done) new-ip updated-instructions)))))

(defn get-lines
        [fname]
        (with-open [r (reader fname)]
                          (doall (line-seq r))))

(defn load-instructions 
  []
  (->> (get-lines "day_5/input.txt")
       (map #(Integer/parseInt %))
       (vec)))

(->> (load-instructions)
     (calculate-steps 0 0 ,,,))
