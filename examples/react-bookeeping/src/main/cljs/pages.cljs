;; pages.cljs
;; Michael Valdron
;; Created on 2021-03-12
(ns pages)

(defn home
  []
  [:div
   [:h1 "Books"]
   [:table.uk-table.uk-table-divider
    [:tr [:th "Title"] [:th "Author"] [:th "Genre"] [:th "Year of Publication"] [:th "Publisher"]]
    [:tr [:td "A Sample Book"] [:td "John Smith"] [:td "Literature"] [:td "1999"] [:td "Publishing Inc."]]]])

(defn not-found
  []
  [:div 
   [:h1 "404 Page Not Found!"]])
