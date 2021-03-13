;; core.cljs
;; Michael Valdron
;; Created on 2021-03-12
(ns core
  (:require [routes]
            [pages]
            [reagent.core :as r]
            [reagent.dom :as rd]))

(defonce app-state (r/atom {}))

(defmulti current-page #(get-in @app-state [:page :name]))
(defmethod current-page :home []
  [pages/home])
(defmethod current-page :not-found []
  [pages/not-found])

(defn ^:dev/after-load init
  []
  (routes/app-router! app-state)
  (rd/render [current-page] (.. js/document (getElementById "root"))))
