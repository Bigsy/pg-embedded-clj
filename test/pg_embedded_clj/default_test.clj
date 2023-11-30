(ns pg-embedded-clj.default-test
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.test :refer [deftest is testing use-fixtures]]
            [pg-embedded-clj.core :as sut]))

(use-fixtures :once sut/with-pg-fn)

(defn around-all
  [f]
  (sut/with-pg-fn f))

(use-fixtures :once around-all)

(def db-spec {:classname   "org.postgresql.Driver"
              :subprotocol "postgresql"
              :subname     (str
                             "//localhost:"
                             5432
                             "/postgres")
              :user        "postgres"})

(defn extract-postgres-version [input]
  (if input
    (if-let [matches (re-matches #".*PostgreSQL (\d+\.\d+).*" input)]
      (second matches)
      "Version not found")
    "No postgres result input"))

(deftest can-wrap-around
  (testing "using defaults"
    (is (= (some-> (jdbc/query db-spec ["select version()"])
                   first
                   :version
                   extract-postgres-version)
           "14.8"))))
