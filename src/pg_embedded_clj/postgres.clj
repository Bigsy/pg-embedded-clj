(ns pg-embedded-clj.postgres
  (:require [integrant.core :as ig]
            [clojure.java.io :as io])
  (:import io.zonky.test.db.postgres.embedded.EmbeddedPostgres
           java.lang.ProcessBuilder$Redirect))

(defn ->pg [port pg-log]
  (let [pg (-> (EmbeddedPostgres/builder)
               (.setLocaleConfig "locale", "en_US.UTF-8")
               (.setPort port))]
    (when pg-log (let [log-redirector (ProcessBuilder$Redirect/appendTo (io/file pg-log))]
                   (-> pg
                       (.setOutputRedirector log-redirector)
                       (.setErrorRedirector log-redirector))))
    (.start pg)))

(defn halt! [pg]
  (when pg
    (.close pg)))

(defmethod ig/init-key ::postgres [_ {:keys [port log-redirect]}]
  (->pg port log-redirect))

(defmethod ig/halt-key! ::postgres [_ pg]
  (halt! pg))

(->pg 9999 nil)