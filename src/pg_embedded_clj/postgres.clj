(ns pg-embedded-clj.postgres
  (:require [integrant.core :as ig]
            [clojure.java.io :as io])
  (:import com.opentable.db.postgres.embedded.EmbeddedPostgres
           java.lang.ProcessBuilder$Redirect))

(defn ->pg [port pg-log silent?]
  (let [pg (-> (EmbeddedPostgres/builder)
               (.setPort port))]
    (when pg-log (let [log-redirector (ProcessBuilder$Redirect/appendTo (io/file pg-log))]
                   (-> pg
                       (.setOutputRedirector log-redirector)
                       (.setErrorRedirector log-redirector))))
    (when silent?
      (.setConnectConfig pg "-s" ""))

    (.start pg)))

(defn halt! [pg]
  (when pg
    (.close pg)))

(defmethod ig/init-key ::postgres [_ {:keys [port log-redirect silent?]}]
  (->pg port log-redirect silent?))

(defmethod ig/halt-key! ::postgres [_ pg]
  (halt! pg))
