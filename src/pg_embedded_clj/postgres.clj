(ns pg-embedded-clj.postgres
  (:require [integrant.core :as ig]
            [clojure.java.io :as io])
  (:import com.opentable.db.postgres.embedded.EmbeddedPostgres
           java.lang.ProcessBuilder$Redirect
           ))

(defn ->pg [port pg-log]
  (if pg-log (let [log-redirector (ProcessBuilder$Redirect/appendTo (io/file pg-log))]
               (-> (EmbeddedPostgres/builder)
                   (.setPort port)
                   (.setOutputRedirector log-redirector)
                   (.setErrorRedirector log-redirector)
                   .start))
             (-> (EmbeddedPostgres/builder)
                 (.setPort port)
                 .start)))

(defn halt! [pg]
  (when pg
    (.close pg)))

(defmethod ig/init-key ::postgres [_ {:keys [port log-redirect]}]
  (->pg port log-redirect))

(defmethod ig/halt-key! ::postgres [_ pg]
  (halt! pg))