(ns highscore.handler
  (:use compojure.core
        [clojure.tools.nrepl.server :only [start-server stop-server]]
        [highscore.page :only [high-scores]])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defn startup []
  ;; Give me an nrepl
  (start-server :port 4444))

(def num-scores 10)
(def scores (atom [{:bot "Le Bot Grande" :score 100}
                   {:bot "BeatBott"      :score 93}
                   {:bot "Edbot"         :score 10 }]))

(defn add-score! [old-scores {b :bot s :score :as m}]
  {:pre [(and b s)]}
  (->> (conj old-scores (assoc m :score (Integer. s)))
       (sort-by :score)
       reverse
       (take num-scores)
       vec))

(defroutes app-routes
  (GET "/" [m]
       (prn m)
       (str m))
  (GET "/high-scores" []
       (high-scores @scores))
  (POST "/high-scores" {p :params :as m}
        (do
          #_(prn m)
          (swap! scores add-score! p)
          "updated"))

  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
