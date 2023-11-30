(ns pg-embedded-clj.custom-test
  (:require [clojure.java.io :as io]
            [clojure.java.jdbc :as jdbc]
            [clojure.test :refer [deftest is testing use-fixtures]]
            [pg-embedded-clj.core :as sut]))

(use-fixtures :once sut/with-pg-fn)

(defn around-all
  [f]
  (io/delete-file "wibble.log" true)
  (sut/with-pg-fn {:port 54321
                   :log-redirect "wibble.log"} f)
  (io/delete-file "wibble.log" true))

(use-fixtures :once around-all)

(def db-spec {:classname   "org.postgresql.Driver"
              :subprotocol "postgresql"
              :subname     (str
                            "//localhost:"
                            54321
                            "/postgres")
              :user        "postgres"})

(defn extract-postgres-version [input]
  (if input
    (if-let [matches (re-matches #".*PostgreSQL (\d+\.\d+).*" input)]
      (second matches)
      "Version not found")
    "No postgres result input"))

(deftest can-wrap-around
  (testing "using custom port"
    (is (= (some-> (jdbc/query db-spec ["select version()"])
                   first
                   :version
                   extract-postgres-version)
           "14.8")))

  (testing "using custom log redirect"
    (is (= true (.exists (io/as-file "wibble.log"))))))
