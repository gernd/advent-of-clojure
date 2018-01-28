(defn compute-value-of-square
[pos squares]
(let [x (get pos :x)
      y (get pos :y)
      neighbour-squares (filter #(and (<= (Math/abs(- (get % :x) x)) 1) (<= (Math/abs(- (get % :y) y)) 1) ) squares)
      square-vals (map #(get % :val) neighbour-squares)
      computed-square-val (reduce + square-vals)]
  computed-square-val))

(defn get-first-value-above-threshold-helper
[threshold squares direction take current-sprint current-steps]
do (println " squares " squares " current steps " current-steps " current-sprint " current-sprint " direction " direction " take " take "threshold" threshold)
  (let [last-square (last squares)
        current-pos 
          (if (= :right direction) {:x (inc (get last-square :x)) :y (get last-square :y)}
          (if (= :up direction) {:x (get last-square :x) :y (inc (get last-square :y))}
          (if (= :down direction) {:x (get last-square :x) :y (dec (get last-square :y))}
          (if (= :left direction) {:x (dec (get last-square :x)) :y (get last-square :y)}))))
        value-of-current-square (compute-value-of-square current-pos squares)
        current-square {:x (get current-pos :x) :y (get current-pos :y) :val value-of-current-square}]
      (if (> value-of-current-square threshold) 
        value-of-current-square
      (if (< current-steps current-sprint)
          (recur threshold (conj squares current-square) direction take current-sprint (inc current-steps))
          (let [next-direction
                  (if (= :right direction) :up
                  (if (= :up direction) :left
                  (if (= :left direction) :down
                  (if (= :down direction) :right))))]
              (if (= take 1)
                  (recur threshold (conj squares current-square) next-direction (inc take) current-sprint 1)
              (if (= take 2)
                  (recur threshold (conj squares current-square) next-direction 1 (inc current-sprint) 1))))))))

(defn get-first-value-above-threshold 
[threshold]
(get-first-value-above-threshold-helper threshold [{:x 0 :y 0 :val 1}] :right 1 1 1))

(get-first-value-above-threshold 325489)
