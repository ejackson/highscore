(ns highscore.client
  (:use [clj-http.client :as client]))

(comment
  (client/get "http://localhost:3000/high-scores")
  (client/post "http://localhost:3000/high-scores" {:form-params {:bot "ed" :score 12}})
  )
