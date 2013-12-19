(defproject romeo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://ddd.pahlevanyan.com/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure     "1.5.1"]
                 [compojure/compojure     "1.0.0"]
                 [ring                    "1.1.8"]
                 [ring/ring-json          "0.2.0"]
                 [ring-mock               "0.1.5"]
                 [ring-middleware-format  "0.3.1"]]
  :plugins      []
  :main romeo.server)
