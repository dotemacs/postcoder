# `postcoder`

A Clojure client for [UK Postcodes](http://www.uk-postcodes.com/) API, an [ODI](http://theodi.org/) project

[![Continuous Integration status](https://secure.travis-ci.org/dotemacs/postcoder.png)](http://travis-ci.org/dotemacs/postcoder)

## Usage

### Get data for a postcode

```clojure
(require '[postcoder.core as postcode])
(postcode/lookup "SW1A 1AA")
```

By default it returns data in JSON, but you can get it in any of the
following formats as well: XML, CSV & RDF, e.g.

```clojure
(postcode/lookup "SW1A 1AA" "json")
(postcode/lookup "SW1A 1AA" "xml")
...
```

### Get data for the nearest postcode at coordinates

```clojure
(require '[postcoder.core as postcode])
(postcode/data-at-point "51.5010091744138" "-0.14157319687256223")
```

By default it returns data in JSON, but you can get it in any of the
following formats as well: XML, CSV & RDF, like so:

```clojure
(postcode/data-at-point "51.5010091744138" "-0.14157319687256223" "json")
(postcode/data-at-point "51.5010091744138" "-0.14157319687256223" "xml")
...
```

### Get data for a postcode within X miles, centered on a postcode

```clojure
(require '[postcoder.core as postcode])
(postcode/area-info :postcode "sw1a 1aa" :miles "0.1")
```

The function above, **area-info**, by default returns data as JSON,
but it also takes an additional argument **:format** which can be any
of: **json**, **xml** or **csv** e.g.

```clojure
(postcode/area-info :postcode "sw1a 1aa" :miles "0.1" :format "json")
```

## License

Released under the [BSD License](http://www.opensource.org/licenses/bsd-license).
