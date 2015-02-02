(ns gorillalabs.config-test
  (:require [gorillalabs.config :refer :all]
            [expectations :refer :all])
  (:import (java.io PushbackReader StringReader)
           (java.net URL)))




(defn reader-from-string "Defines a PushbackReader to read from a string" [s]
  (PushbackReader. (StringReader. s)))



(expect
  [{:a 1} {:b 2} '(1 2 3)]
  (form-seq (reader-from-string "{:a 1} {:b 2} (1 2 3)")))



;; check import file resolution.

(expect
  (URL. "file:///project/config/db.TEST.edn")
  (path-relative-to (URL. "file:///project/config.edn") "config/db.TEST.edn"))

(expect
  (URL. "jar:file:///test.jar!/config/db.TEST.edn")
  (path-relative-to (URL. "jar:file:///test.jar!/config.edn") "config/db.TEST.edn"))

(expect
  (URL. "file:///external/config/db.NODE.edn")
  (path-relative-to (URL. "jar:file:///test.jar!/config.edn") "file:///external/config/db.NODE.edn"))






(expect [['...origin... :a [:b :c]]
         ['...origin... :a nil]
         ]
        (side-effects [invoke-extension]
                      (mapify '(:a :b :c) '...origin...)
                      (mapify '(:a) '...origin...)))


(expect [[(URL. "jar:file:///test.jar!/config/inner-config.edn")]
         ]
        (side-effects [read-config]
                      (mapify (first (form-seq (reader-from-string "(include \"config/inner-config.edn\")")))
                              (URL. "jar:file:///test.jar!/config.edn"))))


(expect {:a 3 :b 2 :c 3}
        (with-redefs [gorillalabs.config/read-config (constantly {:a 3 :c 3})]
          (merge-config (URL. "jar:file:///test.jar!/config.edn")
                        (form-seq (reader-from-string "{:a 1} {:b 2} (include \"config/config.edn\")")))))


(expect {:a 1 :b 2 :c 3}
        (with-redefs [gorillalabs.config/read-config (constantly {:a 3 :c 3})]
          (merge-config (URL. "jar:file:///test.jar!/config.edn")
                        (form-seq (reader-from-string "(include \"config/config.edn\") {:a 1} {:b 2}")))))


(expect {:a 1 :b 2 :test {:x_a 1 :x_b 2}}
        (with-redefs [gorillalabs.config/read-config (constantly {:x_a 1 :x_b 2})]
          (merge-config (URL. "jar:file:///test.jar!/config.edn")
                        (form-seq (reader-from-string "(include-as :test \"config/config.edn\") {:a 1} {:b 2}")))))