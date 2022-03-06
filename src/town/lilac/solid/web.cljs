(ns town.lilac.solid.web
  (:require
   ["solid-js/web" :as web]
   ["solid-js/h" :as h]
   [cljs-bean.core :as b]))


(def render web/render)
(def hydrate web/hydrate)

(def $d h)

(defn $c
  [c & rest]
  (let [c' (fn [props]
             (c (b/bean props)))]
    (apply h c' rest)))
