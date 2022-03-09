(ns town.lilac.solid.web
  (:require
   ["solid-js/web" :as web]
   ["solid-js/h" :as h]
   #_[cljs-bean.core :as b]
   [town.lilac.solid.core :as core]))


(def render web/render)
(def hydrate web/hydrate)

(def $d h)


(defn $c
  [c & rest]
  (let [c' (fn [props]
             (c (core/->Source props)))]
    (apply h c' rest)))
