(ns postcoder.core
  (:require [org.httpkit.client :as http]
            [clojure.string :as string]))

(def api-server "http://uk-postcodes.com")

(defn- add-format
  "append suffix to the URL"
  [& format]
  (let [suffix (first (flatten format))]
      (if (string? suffix)
        suffix
        "json")))

(defn- process-body
  "Return the body of the result based on the suffix"
  [body suffix]
  (if (= suffix "xml")
    body
    (slurp body)))

(defn- fetch-data
  [path suffix]
  (let [url (string/join "." [path suffix])
        result (http/get url)]
    (process-body (:body @result) suffix)))

(defn lookup
  "Query a postcode with the option of getting the results in JSON,
  XML, CSV or RDF. If none is provided, JSON is the default."
  [postcode & format]
  (let [code (string/upper-case (string/replace postcode #"\s+" ""))
        path (string/join "/" [api-server "postcode" code])
        suffix (add-format format)]
    (fetch-data path suffix)))

(defn data-at-point
  "Return information at given latitude & longitude coordinates."
  [lat lng & format]
  (let [point (string/join "," [lat lng])
        path (string/join "/" [api-server "latlng" point])
        suffix (add-format format)]
    (fetch-data path suffix)))

(defn- sanitize-postcode [postcode]
  (string/upper-case (string/replace postcode #"\s+" "")))

(defn- set-area-format [format]
  (if (nil? format)
    "format=json"
    (string/join "" ["format=" format])))

(defn area-info [& {:keys [postcode miles format]}]
  (let [pcode (string/join "=" ["postcode" (sanitize-postcode postcode)])
        path (string/join "/" [api-server "postcode/nearest"])
        first-part (string/join "?" [path pcode])
        with-miles (string/join "=" ["miles" miles])
        url (string/join "&" [first-part with-miles (set-area-format format)])]
    (:body @(http/get url))))
