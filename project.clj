(defproject bigsy/pg-embedded-clj "0.2.0"
  :description "Embedded postgres for clojure"
  :url "https://github.com/Bigsy/pg-embedded-clj"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [integrant "0.8.0"]
                 [org.clojure/tools.logging "1.2.4"]
                 [org.clojure/tools.namespace "1.2.0"]
                 [io.zonky.test/embedded-postgres "1.3.1"]
                 [org.slf4j/slf4j-jdk14 "1.7.35"]]

  :profiles {:dev {:dependencies [[org.clojure/java.jdbc "0.7.12"]]}})
