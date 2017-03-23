# Gorillalabs/Config

Config is a Clojure config file handler.


## Project Goals

Simplify configuration handling.

## Project Maturity

Config is young, but in use at our own projects.


## Artifacts

... are [released to Clojars](https://clojars.org/gorillalabs/config).
If you are using Maven, add the following repository definition to your `pom.xml`:

```xml
<repository>
  <id>clojars.org</id>
  <url>http://clojars.org/repo</url>
</repository>
```

### The Most Recent Release

With Leiningen:

```edn
[gorillalabs/config "1.0.4"]
```

With Maven:

```xml
<dependency>
  <groupId>gorillalabs</groupId>
  <artifactId>config</artifactId>
  <version>1.0.4</version>
</dependency>
```

## Documentation & Examples

Our documentation site is not yet live, sorry.


Basically, it lets you read a config file to produce a Clojure map.
The config files themselves can contain one or more forms, each of which can be either a map or a list.
Maps are simply merged.
Lists correspond to invocations of extension points, which in turn produces a map, which is merged along with everything else.

A sample config file could look like this (see "/example-resources" in our project)

```clojure
(include "config/config.edn")
(include "config/db.TEST.edn")

{:env :local
 :another-key {:map true
               :map-2 42}
 }
```

As you see, you can include other files.


Read the config using this in your code:

```clojure
(gorillalabs.config/init)
```

to read "config.edn" from the classpath; or

```clojure
(gorillalabs.config/init "ENV")
```

to read "config@ENV.edn" from the classpath.

If you do not want to store your config on the classpath, just include a reference to a specific file in your config.edn file like this

```clojure
(include "file:///external/config/some-config.edn")
```


## Community & Support

Gorillalabs is on twitter [@gorillalabs_de](https://twitter.com/gorillalabs_de): Feel
free to join it and ask any questions you may have. We also post announcements of releases, important changes and so on, please follow.


## Supported Clojure versions

config is built from the ground up for Clojure 1.5.1 and up.


## Continuous Integration Status

[![Continuous Integration status](https://secure.travis-ci.org/gorillalabs/config.png)](http://travis-ci.org/gorillalabs/config)



## Development

config uses [Leiningen
2](https://github.com/technomancy/leiningen/blob/master/doc/TUTORIAL.md). Make
sure you have it installed and then run tests against supported
Clojure versions using

    lein all test

Then create a branch and make your changes on it. Once you are done
with your changes and all tests pass, submit a pull request on GitHub.



## License

Copyright (C) 2015 Dr. Christian Betz

Licensed under the [Eclipse Public License](http://www.eclipse.org/legal/epl-v10.html) (the same as Clojure).
