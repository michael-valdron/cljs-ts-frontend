;; app.pages
;; Michael Valdron
;; Created on 2021-03-12
(ns app.pages
  (:require [app.components :as c]))

(defn home
  "Renders view for home page."
  []
  [:div
   [:h1 "Books"]
   [:a.uk-button.uk-button-default {:href "/#/add-book"} "Add Book"]
   (c/view-books)])

(defn not-found
  "Renders view for 404 page."
  []
  [:div 
   [:h1 "404 Page Not Found!"]])

(defn add-book
  "Renders view for add book page."
  []
  [:div
   [:h1 "Add New Book Entry"]
   (c/view-add-book-form)])
