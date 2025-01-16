# pg-embedded-clj

A Clojure library that provides embedded PostgreSQL for testing and development purposes. Based on [zonkyio/embedded-postgres](https://github.com/zonkyio/embedded-postgres), this library makes it easy to spin up and tear down PostgreSQL instances in your Clojure applications.

[![Clojars Project](https://img.shields.io/clojars/v/org.clojars.bigsy/pg-embedded-clj.svg)](https://clojars.org/org.clojars.bigsy/pg-embedded-clj)

## Features

- Simple API for starting and stopping PostgreSQL instances
- Configurable port and logging options
- Support for different PostgreSQL versions and architectures
- Perfect for integration testing and local development
- Automatic cleanup of resources

## Installation

Add the following dependency to your `project.clj`:

```clojure
[org.clojars.bigsy/pg-embedded-clj "latest-version"]
```

## PostgreSQL Version Configuration

By default, this library uses the standard Zonky PostgreSQL version. For most use cases, this default version is sufficient.

To use a specific PostgreSQL version or architecture, include an additional dependency from [zonky-postgres-binaries](https://mvnrepository.com/artifact/io.zonky.test.postgres). For example:

```clojure
;; For PostgreSQL 17 on Apple Silicon
[io.zonky.test.postgres/embedded-postgres-binaries-darwin-arm64v8 "17.0.0"]
```

## Usage

### Development Mode

```clojure
(require '[pg-embedded-clj.core :refer :all])

;; Start PostgreSQL with default settings (port 5432)
(init-pg)

;; Start PostgreSQL with custom configuration
(init-pg {:port 5433
         :log-redirect "postgres.log"})

;; Stop the PostgreSQL instance
(halt-pg!)
```

### Testing

The library provides convenient fixtures for integration testing:

```clojure
(ns your-test-namespace
  (:require [clojure.test :refer :all]
            [pg-embedded-clj.core :refer [with-pg-fn default-config]]))

;; Using the fixture with custom configuration
(defn test-fixture
  [f]
  (with-pg-fn (merge default-config
                     {:port 54321
                      :log-redirect "test.log"})
              f))

(use-fixtures :once test-fixture)

;; Your tests here
(deftest your-integration-test
  (testing "Database operations"
    ;; Your test code here
    ))

;; For ad-hoc testing blocks
(deftest another-test
  (with-pg default-config
    ;; Your test code here
    ))
```

## Configuration Options

The following configuration options are available:

```clojure
{:port 5432          ; PostgreSQL port (default: 5432)
 :log-redirect nil   ; Log file path (default: nil for no logging)}
```

## Related Libraries

Check out these other useful embedded testing libraries for Clojure:

* [dynamo-embedded-clj](https://github.com/Bigsy/dynamo-embedded-clj) - Embedded DynamoDB
* [redis-embedded-clj](https://github.com/Bigsy/redis-embedded-clj) - Embedded Redis
* [s3-clj](https://github.com/Bigsy/s3-clj) - Embedded S3
* [elasticmq-clj](https://github.com/Bigsy/elasticmq-clj) - Embedded ElasticMQ
* [sns-clj](https://github.com/Bigsy/sns-clj) - Embedded SNS

## License

Copyright © 2024

Distributed under the Eclipse Public License, the same as Clojure.
