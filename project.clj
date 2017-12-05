(defproject bigsy/pg-embedded-clj "0.0.4"
  :description "Embedded postgres for clojure"
  :url "https://github.com/Bigsy/pg-embedded-clj"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-RC2"]
                 [integrant "0.6.1"]
                 [org.clojure/tools.logging "0.4.0"]
                 [org.clojure/tools.namespace "0.2.11"]
                 [com.opentable.components/otj-pg-embedded "0.9.0"]
                 [org.slf4j/slf4j-jdk14 "1.7.25"]]

  :profiles {:dev {:dependencies [[org.clojure/java.jdbc "0.7.1"]]}})
