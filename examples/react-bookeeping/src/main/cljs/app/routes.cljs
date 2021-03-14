;; app.routes
;; Michael Valdron
;; Created on 2021-03-12
(ns app.routes
  (:require [bide.core :as bide]))

(def routes
  "Routes on website."
  (bide/router [["/" :home]
                ["/add-book" :add-book]]))

(defn- on-nav
  [state & data]
  (swap! state assoc :page (zipmap [:name :params :query] data)))

(defn app-router!
  "The website router sets the view to a given URL."
  [state]
  (bide/start! routes {:default :not-found
                       :on-navigate (partial on-nav state)}))
