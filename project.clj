(defproject bigsy/pg-embedded-clj "0.1.0"
  :description "Embedded postgres for clojure"
  :url "https://github.com/Bigsy/pg-embedded-clj"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.2"]
                 [integrant "0.8.0"]
                 [org.clojure/tools.logging "1.1.0"]
                 [org.clojure/tools.namespace "1.1.0"]
                 [io.zonky.test/embedded-postgres "1.2.10"]
                 [org.slf4j/slf4j-jdk14 "1.7.30"]]

  :profiles {:dev {:dependencies [[org.clojure/java.jdbc "0.7.12"]]}})
