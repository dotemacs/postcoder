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
following formats as well: XML, CSV & RDF, like so:

```clojure
(postcode/lookup "SW1A 1AA" "json")
(postcode/lookup "SW1A 1AA" "xml")
(postcode/lookup "SW1A 1AA" "csv")
(postcode/lookup "SW1A 1AA" "rdf")
```

### Get data for the nearest postcode at coordinates

```clojure
(require '[postcoder.core as postcode])
(postcode/data-at-point "51.5010091744138" "-0.14157319687256223")
```

## License

Released under the [BSD License](http://www.opensource.org/licenses/bsd-license.php).
