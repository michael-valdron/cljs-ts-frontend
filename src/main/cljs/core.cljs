;; core.cljs
;; Michael Valdron
;; Created on 2021-03-12
(ns core
  (:require ["./index" :as ts]))

(defn ^:dev/after-load init
  []
  (ts/init)
  (println "Hello from ClojureScript"))
