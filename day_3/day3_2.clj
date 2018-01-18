(defn get-value-of-square 
[]
10)

(defn get-first-value-above-threshold-helper
[threshold steps-total position direction take current-sprint current-steps]
do (println "steps-total " steps-total " position " position " current steps " current-steps " current-sprint " current-sprint " direction " direction " take " take "threshold" threshold)
  (let [value-of-current-square (get-value-of-square)]
    (if (> value-of-current-square threshold) 
        value-of-current-square
    (let [next-pos 
            (if (= :right direction) {:x (inc (get position :x)) :y (get position :y)}
            (if (= :up direction) {:x (get position :x) :y (inc (get position :y))}
            (if (= :down direction) {:x (get position :x) :y (dec (get position :y))}
            (if (= :left direction) {:x (dec (get position :x)) :y (get position :y)}))))]
        (if (< current-steps current-sprint)
            (recur threshold (inc steps-total) next-pos direction take current-sprint (inc current-steps))
            (let [next-direction
                    (if (= :right direction) :up
                    (if (= :up direction) :left
                    (if (= :left direction) :down
                    (if (= :down direction) :right))))]
                (if (= take 1)
                    (recur threshold (inc steps-total) next-pos next-direction (inc take) current-sprint 1)
                (if (= take 2)
                    (recur threshold (inc steps-total) next-pos next-direction 1 (inc current-sprint) 1)))))))))

(defn get-first-value-above-threshold 
[threshold]
(get-first-value-above-threshold-helper threshold 1 {:x 0 :y 0} :right 1 1 1))

(get-first-value-above-threshold 5)
