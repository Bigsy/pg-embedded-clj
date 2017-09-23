(ns pg-embedded-clj.core-test
  (:require [clojure.test :refer :all]
            [clojure.java.jdbc :as jdbc]
            [pg-embedded-clj.core :as sut]
            [pg-embedded-clj.core :refer :all]))

(use-fixtures :once with-pg-fn)

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
      (is (= {:version
              "PostgreSQL 9.6.3 on x86_64-pc-linux-gnu, compiled by gcc (GCC) 4.4.7 20120313 (Red Hat 4.4.7-16), 64-bit"}
             (first (jdbc/query db-spec ["select version()"]))))))