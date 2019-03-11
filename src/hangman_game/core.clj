(ns hangman-game.core
  (:gen-class))

(def life-total 6)

(def secret-word "MELANCIA")

(defn lose []
  (print "Voce Perdeu!"))

(defn wins []
  (print "Voce Ganhou!"))

(defn read! [] (read-line))

(defn print-hangman [life word shots]
  (println "Vidas " life)
  (doseq [letter (seq word)]
    (if (contains? shots (str letter))
      (print letter " ")
      (print "_" " ")))
  (println))

(defn get-letters-remaining [word shots]
    (remove (fn [letter] (contains? shots (str letter))) word))

(defn is-complete? [word shots]
    (empty? (get-letters-remaining word shots)))

(defn contains-letter?[shot, word]
  (.contains word shot))

(defn start [life word shots]
    (print-hangman life word shots)
    (cond 
        (= life 0) (lose)
        (is-complete? word shots) (wins)
        :else
        (let [shot (read!)]
          (if (contains-letter? shot word)
            (do
              (println "Acertou a letra!")
              (recur life word (conj shots shot))
            )
            (do
              (println "Errou a letra! Perdeu vida!")
              (recur (dec life) word shots))))))
          
(defn play [] (start life-total secret-word #{}))

(defn -main [& args]
  (play))
