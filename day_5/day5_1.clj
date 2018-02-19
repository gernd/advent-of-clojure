(defn calculate-steps 
[steps-done ip instructions]
  (do (println "steps-done " steps-done " instruction pointer " ip " instructions " instructions)
    (if (or (< ip 0)(>= ip (count instructions)))
      steps-done 
    (let  [offset (get instructions ip)
          new-ip (+ ip offset)
          updated-offset (inc offset)
          updated-instructions (assoc instructions ip updated-offset)]
        (calculate-steps (inc steps-done) new-ip updated-instructions)))))

(calculate-steps 0 0 [0, 3, 0, 1, -3])
