;; app.core
;; Michael Valdron
;; Created on 2021-03-12
(ns app.core
  (:import )
  (:require [app.routes :as routes]
            [app.pages :as pages]
            [app.db :as db]
            [reagent.core :as r]
            [reagent.dom :as rd]
            [web.url.URL :as url]))

(defonce app-state (r/atom {}))

(defn- trim-query!
  []
  (let [trimmed-uri (.. (url/href (.-location js/window)) (substr 0 (.. (url/href (.-location js/window)) (indexOf "?"))))]
    (url/set-href! (.-location js/window) trimmed-uri)))

(defmulti current-page #(get-in @app-state [:page :name]))
(defmethod current-page :home []
  (do (when (-> @app-state (get-in [:page :query]) (db/query!))
        (trim-query!))
      [pages/home]))
(defmethod current-page :add-book []
  [pages/add-book])
(defmethod current-page :not-found []
  [pages/not-found])

(defn ^:dev/after-load init
  []
  (routes/app-router! app-state)
  (rd/render [current-page] (.. js/document (getElementById "root"))))
