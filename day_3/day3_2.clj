(defn get-value-of-square
[squares]
(count squares))

(defn get-first-value-above-threshold-helper
[threshold squares direction take current-sprint current-steps]
do (println " squares " squares " current steps " current-steps " current-sprint " current-sprint " direction " direction " take " take "threshold" threshold)
  (let [position (last squares)
        next-pos 
          (if (= :right direction) {:x (inc (get position :x)) :y (get position :y)}
          (if (= :up direction) {:x (get position :x) :y (inc (get position :y))}
          (if (= :down direction) {:x (get position :x) :y (dec (get position :y))}
          (if (= :left direction) {:x (dec (get position :x)) :y (get position :y)}))))
        value-of-current-square (get-value-of-square squares)]
      (if (> value-of-current-square threshold) 
        value-of-current-square
      (if (< current-steps current-sprint)
          (recur threshold (conj squares next-pos) direction take current-sprint (inc current-steps))
          (let [next-direction
                  (if (= :right direction) :up
                  (if (= :up direction) :left
                  (if (= :left direction) :down
                  (if (= :down direction) :right))))]
              (if (= take 1)
                  (recur threshold (conj squares next-pos) next-direction (inc take) current-sprint 1)
              (if (= take 2)
                  (recur threshold (conj squares next-pos) next-direction 1 (inc current-sprint) 1))))))))

(defn get-first-value-above-threshold 
[threshold]
(get-first-value-above-threshold-helper threshold [{:x 0 :y 0}] :right 1 1 1))

(get-first-value-above-threshold 10)
