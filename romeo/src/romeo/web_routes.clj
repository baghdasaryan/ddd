(ns romeo.web-routes
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
       (resource-response "homepage.html" {:root "public/homepage"}))


;;; =================== ;;;
;;;                     ;;;
;;;    Data Requests    ;;;
;;;                     ;;;
;;; =================== ;;;

  (POST "/" [user :as {req-doc :params
                       user-ip :remote-addr}]
        (banner "POST A GAME REQUEST")
        (add-body
          (if (or (nil? user) (nil? user-ip))
            {:status "Request failed"}
            {:user user
             :user-ip user-ip
             :status "Request succeeded"})))
)
