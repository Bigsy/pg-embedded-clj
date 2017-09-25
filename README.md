# pg-embedded-clj

Embedded postgres for clojure - based on otj-pg-embedded

## Usage

[![Clojars Project](https://img.shields.io/clojars/v/bigsy/pg-embedded-clj.svg)](https://clojars.org/bigsy/pg-embedded-clj)

### Development:

```clojure
(require 'pg-embedded-clj.core)

;; Start an embedded pg with default port:
(init-pg)

;; another call will halt the previous system:
(init-pg)

;; When you're done:
(halt-pg!)
```

### Testing:

**NOTE**: these will halt running pg-embedded instances

```clojure
(require 'clojure.test)

(use-fixtures :once with-pg-fn)

(defn around-all
  [f]
  (with-pg-fn (merge default-config
                           {:port 54321
                           :log-redirect "wibble.log"})
                    f))

(use-fixtures :once around-all)

;;; You can also wrap ad-hoc code in init/halt:
(with-pg default-config
	,,, :do-something ,,,)
```

