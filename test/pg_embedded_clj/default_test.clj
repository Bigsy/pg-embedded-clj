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
(deftest can-wrap-around
  (testing "using defaults"
      (is (= {:version "PostgreSQL 14.5 on x86_64-pc-linux-gnu, compiled by gcc (Ubuntu 4.8.4-2ubuntu1~14.04.4) 4.8.4, 64-bit"}
             (first (jdbc/query db-spec ["select version()"]))))))
