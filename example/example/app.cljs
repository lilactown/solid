(ns example.app
  (:require
   [town.lilac.solid.core :as s]
   [town.lilac.solid.web :as web :refer [$]]))


(defn app
  []
  (let [[count set-count] (s/signal 0)]
    ($ "div"
       count
       ($ "button"
          #js {:onClick (fn [_] (set-count inc))}
          "hi"))))


(defonce cleanup identity)


(defn ^:dev/after-load start!
  []
  (cleanup)
  (set! cleanup (web/render app (js/document.getElementById "app"))))
