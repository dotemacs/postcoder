# `postcoder`

A Clojure client for [UK Postcodes](http://www.uk-postcodes.com/) API, an [ODI](http://theodi.org/) project

[![Continuous Integration status](https://secure.travis-ci.org/dotemacs/postcoder.png)](http://travis-ci.org/dotemacs/postcoder)

## Usage

### Get data for a postcode

```clojure
(require '[postcoder.core as postcode])
(postcode/lookup "SW1A 1AA" "json")
```

## License

Released under the [BSD License](http://www.opensource.org/licenses/bsd-license.php).
