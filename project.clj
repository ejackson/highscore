(defproject highscore "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/tools.nrepl "0.2.0-RC1"]
                 [compojure "1.1.5"]
                 [clj-http "0.7.2"]
                 [hiccup "1.0.0"]]
  :plugins [[lein-ring "0.8.3"]]
  :ring {:handler highscore.handler/app
         :init    highscore.handler/startup}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.3"]]}})
