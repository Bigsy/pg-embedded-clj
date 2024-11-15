(defproject org.clojars.bigsy/pg-embedded-clj "1.0.2"
  :description "Embedded postgres for clojure"
  :url "https://github.com/Bigsy/pg-embedded-clj"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.12.0"]
                 [integrant "0.13.1"]
                 [io.zonky.test/embedded-postgres "2.0.7"]
                 [org.clojure/tools.logging "1.3.0"]
                 [org.clojure/tools.namespace "1.5.0"]]


  :profiles {:dev {:dependencies [[org.clojure/java.jdbc "0.7.12"]]}})
