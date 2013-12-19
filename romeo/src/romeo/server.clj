(ns romeo.server
  (:require [ring.adapter.jetty :refer [run-jetty]])
  (:use [ring.middleware format reload stacktrace file file-info]
        [romeo.config]
        [romeo.web-routes])
  (:gen-class))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;                    ;;;;
;;;;    ROMEO SERVER    ;;;;
;;;;                    ;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(def app
  (->  app-routes
    (wrap-reload '(romeo.server romeo.web-routes))
    ;(wrap-file "resources")
    (wrap-file-info)
    (wrap-restful-format :formats [:json-kw :edn :yaml-kw :yaml-in-html])
    (wrap-stacktrace)))

(defn -main
  "Starts server on default port number, unless one was provided as the first
  argument."
  [& [port]]
  (println "Starting Jetty server!")
  (run-jetty (-> #'app)
             {:port (if port (read-string port) default-port-number)
;              :host "localhost"
              :join? false}))
