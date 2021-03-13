;; routes.cljs
;; Michael Valdron
;; Created on 2021-03-12
(ns routes
  (:require [bide.core :as bide]))

(def routes
  (bide/router [["/" :home]]))

(defn- on-nav
  [state & data]
  (swap! state assoc :page (zipmap [:name :params :query] data)))

(defn app-router!
  [state]
  (bide/start! routes {:default :not-found
                       :on-navigate (partial on-nav state)}))
