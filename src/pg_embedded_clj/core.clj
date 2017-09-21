(ns pg-embedded-clj.core
  (:require [clojure.pprint :as pprint]
            [clojure.tools.logging :as log]
            [integrant.core :as ig]
            [pg-embedded-clj.state :as state]
            [clojure.java.io :as io])
  (:import com.opentable.db.postgres.embedded.EmbeddedPostgres
           java.lang.ProcessBuilder$Redirect))

(def pg-log "embedded_postgres.log")


(defn start []
  (let [log-redirector (ProcessBuilder$Redirect/appendTo (io/file pg-log))

        pg             (-> (EmbeddedPostgres/builder)
                           (.setOutputRedirector log-redirector)
                           (.setErrorRedirector log-redirector)
                           .start)]))

(defn stop
  [embedded-db]
  (.close (:embedded-pg embedded-db)))
