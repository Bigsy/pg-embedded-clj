(defproject org.clojars.bigsy/pg-embedded-clj "1.0.0"
  :description "Embedded postgres for clojure"
  :url "https://github.com/Bigsy/pg-embedded-clj"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [integrant "0.8.0"]
                 [io.zonky.test/embedded-postgres "2.0.1"]
                 [org.clojure/tools.logging "1.2.4"]
                 [org.clojure/tools.namespace "1.3.0"]
                 [org.slf4j/slf4j-jdk14 "2.0.1"]]

  :profiles {:dev {:dependencies [[org.clojure/java.jdbc "0.7.12"]]}})
