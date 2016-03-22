(defproject
  gorillalabs/config
  "1.0.1"
  :description "A Clojure config file handler"
  :scm {:name "git"
        :url  "https://github.com/gorillalabs/config"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :profiles {:master {:dependencies [[org.clojure/clojure "1.8.0"]
                                     [org.clojure/tools.logging "0.3.1"]
                                     ]}
             :dev    {:dependencies   [[expectations "2.0.8"]]
                      :resource-paths ["test/resources"]
                      :plugins        [[codox "0.6.4"]
                                       [lein-expectations "0.0.7"]]
                      :codox          {:sources    ["src/clojure"]
                                       :output-dir "doc/api"}}}
  :aliases {"all"  ["with-profile" "dev:dev,1.6:dev,master"]
            "test" ["do" "clean" ["with-profiles" "+test" "expectations"]]
            }
  :repositories {"sonatype"           {:url       "http://oss.sonatype.org/content/repositories/releases"
                                       :snapshots false
                                       :releases  {:checksum :fail}}
                 "sonatype-snapshots" {:url       "http://oss.sonatype.org/content/repositories/snapshots"
                                       :snapshots true
                                       :releases  {:checksum :fail :update :always}}}
  :javac-options ["-target" "1.6" "-source" "1.6"]
  :jvm-opts ["-Dfile.encoding=utf-8"]
  :source-paths ["src/clojure"]
  :java-source-paths ["src/java"]
  :lein-release {:deploy-via :clojars}
  )
