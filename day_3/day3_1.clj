(defn get-position
[cell-number steps-total position direction take current-sprint current-steps]
do (println "steps-total " steps-total " position " position " current steps " current-steps " current-sprint " current-sprint " direction " direction " take " take)
    (if (= steps-total cell-number) 
        position
    (let [next-pos 
            (if (= :right direction) {:x (inc (get position :x)) :y (get position :y)}
            (if (= :up direction) {:x (get position :x) :y (inc (get position :y))}
            (if (= :down direction) {:x (get position :x) :y (dec (get position :y))}
            (if (= :left direction) {:x (dec (get position :x)) :y (get position :y)}))))]
        (if (< current-steps current-sprint)
            (recur cell-number (inc steps-total) next-pos direction take current-sprint (inc current-steps))
            (let [next-direction
                    (if (= :right direction) :up
                    (if (= :up direction) :left
                    (if (= :left direction) :down
                    (if (= :down direction) :right))))]
                (if (= take 1)
                    (recur cell-number (inc steps-total) next-pos next-direction (inc take) current-sprint 1)
                (if (= take 2)
                    (recur cell-number (inc steps-total) next-pos next-direction 1 (inc current-sprint) 1))))))))
                    
(defn get-manhattan-distance
[number]
(let [coordinates (get-position number 1 {:x 0 :y 0} :right 1 1 1)]
      do( println "Coordinates " coordinates)
      (+ (Math/abs (get coordinates :x)) (Math/abs (get coordinates :y)))))

(get-manhattan-distance 325489)
