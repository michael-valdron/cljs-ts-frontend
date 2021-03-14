;; app.db
;; Michael Valdron
;; Created on 2021-03-13
(ns app.db
  (:require [clojure.string :as string]
            [reagent.core :as r]
            [cljs-time.core :as dt]
            [cljs-time.coerce :as dtc]))

(defonce books
  (r/atom [{:title "A Sample Book"
            :author "John Doe"
            :abstract "The sample book is the best book of them all!"
            :genre "Literature"
            :date (dt/date-time 1999 9 2)
            :publisher "Publishing Inc."}
           {:title "Another Sample Book"
            :author "Thomas Anderson"
            :genre "Computers"
            :date (dt/date-time 2003 4 23)
            :publisher "Publishing Inc."}
           {:title "An Unknown Book"
            :author "Jane Doe"}]))

(defn- nil-if-empty
  [s]
  (when-not (string/blank? s) s))

(defn- create-date-from-str
  [s]
  (when-let [date-str (nil-if-empty s)]
    (dtc/to-local-date date-str)))

(defn- run-add-book!
  [q]
  (let [new-book (-> (dissoc q :action)
                     (update :abstract nil-if-empty)
                     (update :genre nil-if-empty)
                     (update :date create-date-from-str)
                     (update :publisher nil-if-empty))]
    (swap! books conj new-book))
  true)

(defn query!
  "Tries to perform a query based on a given URL query params, returns true if performed, returns false if a problem, returns nil if query params is nil."
  [q]
  (when-not (nil? q)
    (when-let [action (q :action)]
      (condp = action
        "add_book" (run-add-book! q)
        false))))
