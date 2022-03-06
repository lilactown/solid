(ns example.app
  (:require
   [goog.object :as gobj]
   [town.lilac.solid.core :as s]
   [town.lilac.solid.web :as web :refer [$d $c]]))


(defn counter
  [props]
  (let [initial-count (:initial-count props)
        [count set-count] (s/signal initial-count)]
    (s/effect #(set-count (:initial-count props)))
    ($d "div"
        "Count: "
        count
        ($d "button"
            #js {:onClick (fn [_] (set-count inc))}
            "+"))))


(defn app
  []
  (let [[initial-count set-initial-count] (s/signal 4)]
    ($d "div"
        "Starting count: "
        ($d "input" #js {:onChange (fn [e]
                                     (set-initial-count
                                      (js/parseInt (.. e -target -value))))
                         :value initial-count
                         :type "number"})
        ($c counter #js {:initial-count initial-count}))))


(defonce cleanup identity)


(defn ^:dev/after-load start!
  []
  (cleanup)
  (set! cleanup (web/render app (js/document.getElementById "app"))))
