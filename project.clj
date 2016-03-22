(defproject
  gorillalabs/config
  "1.0.2"
  :description "A Clojure config file handler"
  :scm {:name "git"
        :url  "https://github.com/gorillalabs/config"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.logging "0.3.1"]]
  :profiles {:dev    {:dependencies   [[expectations "2.0.8"]]
                      :resource-paths ["test/resources"]
                      :plugins        [[codox "0.6.4"]
                                       [lein-expectations "0.0.7"]]
                      :codox          {:sources    ["src/clojure"]
                                       :output-dir "doc/api"}}}
  :aliases {
            "test" ["do" "clean" ["with-profiles" "+test" "expectations"]]
            }
  :javac-options ["-target" "1.6" "-source" "1.6"]
  :jvm-opts ["-Dfile.encoding=utf-8"]
  :lein-release {:deploy-via :clojars}
  )
