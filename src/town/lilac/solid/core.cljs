(ns town.lilac.solid.core
  (:require
   ["solid-js" :as s]
   [goog.object :as gobj])
  (:refer-clojure :exclude [merge]))


;;
;; core reactive signal fns
;;

(defn signal
  "Creates a new reactive value given an initial value.
  Returns a tuple [getter setter]. Other reactive functions that call getter
  will update when setter is called with a new value.

  A second argument of options can be passed.
  See https://www.solidjs.com/docs/latest/api#createsignal"
  [initial]
  (s/createSignal initial))

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


(defprotocol ISource
  (-unwrap [x]))


(deftype Source [props]
  ;; TODO handle ns kw
  IFn
  (-invoke [_ k]
    (gobj/get props (name k)))
  (-invoke [_ k not-found]
    (gobj/get props (name k) not-found))

  ISource
  (-unwrap [_] props))


(extend-protocol ISource
  ;; TODO shallowly convert to and from JS obj
  PersistentArrayMap
  (-unwrap [x] x)
  PersistentHashMap
  (-unwrap [x] x)
  PersistentTreeMap
  (-unwrap [x] x)

  object
  (-unwrap [x] x))


(defn merge
  ([source]
   (->Source (s/mergeProps (-unwrap source))))
  ([source1 source2]
   (->Source (s/mergeProps (-unwrap source1) (-unwrap source2))))
  ([source1 source2 & sources]
   (->Source
    (apply s/mergeProps (-unwrap source1) (-unwrap source2) (map -unwrap sources)))))


(defn split
  ([source ks]
   ;; TODO handle ns kw
   (map ->Source (s/splitProps (-unwrap source) (map name ks))))
  ([source ks & more]
   (map ->Source
        (apply s/splitProps (-unwrap source) (map name ks) (map #(map name %) more)))))


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
