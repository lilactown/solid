(ns town.lilac.solid.core
  (:require
   ["solid-js" :as s]))

;;
;; core reactive signal fns
;;

(def signal
  "Creates a new reactive value given an initial value.
  Returns a tuple [getter setter]. Other reactive functions that call getter
  will update when setter is called with a new value.

  A second argument of options can be passed.
  See https://www.solidjs.com/docs/latest/api#createsignal"
  s/createSignal)

(def effect
  "Register a new side effect to run on change of any reactive values used
  during its execution.

  See https://www.solidjs.com/docs/latest/api#createeffect"
  s/createEffect)

(def memo
  "Creates a new read-only value which will update when any other reactive
  values used in its computation are changed.

  See https://www.solidjs.com/docs/latest/api#creatememo"
  s/createMemo)

(def resource
  ""
  s/createResource)

;;
;; reactive utils
;;

(def untrack s/untrack)
(def batch s/batch)
(def on s/on)
(def root s/createRoot)
(def current-owner s/getOwner)
(def run-with-owner s/runWithOwner)
;; TODO make this cljs friendly
(def merge-props s/mergeProps)
(def split-props s/splitProps)
;; /end todo
(def transition s/useTransition)
(def start-transition s/startTransition)

;; TODO make this cljs friendly
(def map-array s/mapArray)
(def index-array s/indexArray)

;;
;; lifecycle fns
;;

(def on-mount s/onMount)
(def on-cleanup s/onCleanup)
(def on-error s/onError)
