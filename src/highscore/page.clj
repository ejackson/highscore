(ns highscore.page
  (:use [hiccup.page :only [include-css include-js html5]]))

(defn layout [& content]
  (html5
   [:head
    [:title "Console"]
    [:link {:href "css/tron.css" :rel "stylesheet" :media "screen"}]]

   [:body [:center [:div content ]]]))

(defn map-tag [tag xs]
  (map (fn [x] [tag x]) xs))

(defn html-table
  "Create an html table from a regular vector of maps"
  [xs]
  (let [headings (keys (first xs))]
      (list
       [:table {:style "border: 0; cell padding 100;  cell-spacing 100;"}
        [:tr (map-tag :th headings)]
        (map (fn [row]
               (->> (map (fn [h] (row h)) headings)
                    (map-tag :td)
                    (cons :tr)
                    vec))
             xs)])))

(defn high-scores
  [scores]
  (->> scores html-table layout))
