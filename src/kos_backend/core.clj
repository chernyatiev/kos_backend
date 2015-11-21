(ns kos-backend.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

;; function
(defn square [x] (* x x))


(def person 
            {:name "Mark Volkmann"
             :address {:street "644 Glen Summit"
                       :city "St. Charles"
                       :state "Missouri"
                       :zip 63304}
             :employer {:name "Object Computing, Inc."
                        :address {:street "12140 Woodcrest Dr."
                                  :city "Creve Coeur"
                                  :state "Missouri"
                                  :zip 63141}}})

(((person :employer) :address) :city) 

(comp square inc)

;; threading macro
(-> "this is my string" 
  (.toUpperCase)
  (reverse)
  (clojure.string/join))

(-> person :employer :address :city)


(def p (->> (range)
       (map square)
       (filter even?)
       (map inc)))

;;lazy seq
(take 10 p)

;;evaluation
(def foo "(+ 1 2 3)")

(eval foo)   

(eval (read-string foo))


(defn infix2
   [infixed]
    (eval (list (second infixed)
       (first infixed)
       (last infixed))))


;;Macros
 (defmacro infix
   [infixed]
    (list (second infixed) 
       (first infixed)
       (last infixed)))

(macroexpand '(infix (1 + 1)))

;;Atoms
(def my-atom (atom 0))

(swap! my-atom inc)

(swap! my-atom (fn [n] (* (+ n n) 2)))

;;Refs

(def acc1 (ref 100))
(def acc2 (ref 200))

(println @acc1 @acc2)

(defn transfer-money 
 [a1 a2 amount]
  (dosync
    (alter a1 - amount)
    (alter a2 + amount)
    amount))


(def f (future (Thread/sleep 10000) 
       (println "done") 100))

(realized? f)




