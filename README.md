# pg-embedded-clj

Embdded postgres for clojure based on otj-pg-embedded


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
                           {:port 54321})
                    f))

(use-fixtures :once around-all)

;;; You can also wrap ad-hoc code in init/halt:
(with-pg default-config
	,,, :do-something ,,,)
```

