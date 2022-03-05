(ns example.app
  (:require
   [town.lilac.solid.web :as web]))


(defn app
    []
    (web/$ "div" nil "hi"))


(defn ^:dev/after-load start!
  []
  (web/render app (js/document.getElementById "app")))
