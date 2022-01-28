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
(deftest can-wrap-around
  (testing "using custom port"
    (is (= {:version "PostgreSQL 10.18 on x86_64-pc-linux-gnu, compiled by gcc (GCC) 4.4.7 20120313 (Red Hat 4.4.7-23), 64-bit"}
           (first (jdbc/query db-spec ["select version()"])))))

  (testing "using custom log redirect"
    (is (= true (.exists (io/as-file "wibble.log"))))))
