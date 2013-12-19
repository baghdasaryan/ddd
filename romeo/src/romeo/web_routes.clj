(ns romeo.web-routes
  (:require
    [clojure.java.io :as io])
  (:use
    [ring.util.response     ]
    [ring.middleware.json   ]
    [ring.middleware.reload ]
    [compojure.core  :only (GET HEAD PUT POST DELETE ANY defroutes)]
    [compojure.route :only (resources)]))


;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;                  ;;;;
;;;;    WEB ROUTES    ;;;;
;;;;                  ;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;


(defn- banner
  "Prints out a separator followed by message \"msg\" on the next line."
  [msg]
  (println "================================================================")
  (println msg))

(defn- add-body
  "If \"data\" is not nil, produces a map with \"data\" appended to :body,
  otherwise returns nil"
  [data]
  (when-not (nil? data)
    {:body data}))

(defroutes app-routes

  (resources "/")

;;; ======================= ;;;
;;;                         ;;;
;;;    Homepage Requests    ;;;
;;;                         ;;;
;;; ======================= ;;;

  (GET "/" []
       (banner "GET HOMEPAGE")
       (resource-response "homepage.html" {:root ""}))


;;; ======================= ;;;
;;;                         ;;;
;;;    Homepage Requests    ;;;
;;;                         ;;;
;;; ======================= ;;;


)
