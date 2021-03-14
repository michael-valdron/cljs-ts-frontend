;; app.components
;; Michael Valdron
;; Created on 2021-03-13
(ns app.components
  (:require ["./components/Book" :refer [Book]]
            ["./components/AddBookForm" :refer [AddBookForm]]
            [app.db :as db]
            [reagent.core :as r]))

(defn- render-books
  []
  (mapv #(vector (r/adapt-react-class Book) %) @db/books))

(defn view-books
  "Renders view for books table component."
  []
  (-> [:table.uk-table.uk-table-divider
       [:tr [:th "Title"] [:th "Author"] [:th "Abstract"] [:th "Genre"] [:th "Year of Publication"] [:th "Publisher"]]]
      (concat (render-books))
      (vec)))

(defn view-add-book-form
  "Renders view for add book form component."
  []
  [(r/adapt-react-class AddBookForm) {}])
