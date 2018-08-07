(ns pg-embedded-clj.core
  (:require [clojure.pprint :as pprint]
            [clojure.tools.logging :as log]
            [integrant.core :as ig]
            [pg-embedded-clj.state :as state]))

(def default-config
  {:port         5432
   :log-redirect nil})

(defn ->ig-config [config]
  {:pg-embedded-clj.postgres/postgres {:port (:port config)
                                       :log-redirect (:log-redirect config)
                                       :silent? (:silent? config)}})

(defn halt-pg! []
  (when @state/state
    (swap! state/state
           (fn [s]
             (ig/halt! (:system s))
             nil))))

(defn init-pg
  ([] (init-pg default-config))
  ([config]
   (let [ig-config (->ig-config config)
         config-pp (with-out-str (pprint/pprint config))]
     (log/info "starting embedded-pg with config:" config-pp)
     (try
       (halt-pg!)
       (ig/load-namespaces ig-config)
       (reset! state/state
               {:system (ig/init ig-config)
                :config ig-config})
       (catch clojure.lang.ExceptionInfo ex
         (ig/halt! (:system (ex-data ex)))
         (throw (.getCause ex)))))))

(defn with-pg-fn
  "Startup with the specified configuration; executes the function then shuts down."
  ([config f]
   {:pre [(map? config) (fn? f)]}
   (try
     (init-pg config)
     (f)
     (finally
       (halt-pg!))))
  ([f]
   (with-pg-fn default-config f)))

(defmacro with-pg
  "Startup with the specified configuration; executes the body then shuts down."
  [config & body]
  `(with-pg-fn ~config (fn [] ~@body)))
