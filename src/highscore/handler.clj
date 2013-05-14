(ns highscore.handler
  (:use compojure.core
        [highscore.page :only [high-scores]])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(def num-scores 10)
(def scores (atom [{:bot "Le Bot Grande" :score 100}
                   {:bot "ShnellBott"    :score 93}
                   {:bot "Edbot"         :score 0 }]))

(defn add-score! [old-scores {b :bot s :score :as m}]
  {:pre [(and b s)]}
  (->> (conj old-scores m)
       (sort-by :score)
       reverse
       (take num-scores)
       vec))

(defroutes app-routes
  (GET "/high-scores" []
       (high-scores @scores))
  (POST "/high-scores" {p :params}
        (swap! scores add-score! p))

  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
