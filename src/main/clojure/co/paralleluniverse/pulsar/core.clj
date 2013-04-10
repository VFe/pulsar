(ns co.paralleluniverse.pulsar.core
  (:import [co.paralleluniverse.lwthreads LightweightThread]
           [co.paralleluniverse.lwthreads.channels Channel]
           [co.paralleluniverse.lwthreads.channels ObjectChannel]
           [co.paralleluniverse.lwthreads.channels IntChannel]
           [co.paralleluniverse.lwthreads.channels LongChannel]
           [co.paralleluniverse.lwthreads.channels FloatChannel]
           [co.paralleluniverse.lwthreads.channels DoubleChannel]
           [co.paralleluniverse.actors PulsarActor]
           [co.paralleluniverse.actors ActorTarget]))

(use '[clojure.core.match :only (match)])


;; ## lightweight threads

(defn available-processors
  "Returns the number of available processors"
  []
  (.availableProcessors (Runtime/getRuntime)))

;; A global forkjoin pool
(def fj-pool
  (jsr166e.ForkJoinPool. (available-processors) jsr166e.ForkJoinPool/defaultForkJoinWorkerThreadFactory nil true))

(defn self
  "Returns the currently running lightweight-thread or nil if none"
  []
  (LightweightThread/currentLightweightThread))

;; ## Actors

(def actor1 
  (PulsarActor. "actor1" *fj-pool* -1 -1 
          (reify ActorTarget
            (run [this self]
                 (.receive self)))))





