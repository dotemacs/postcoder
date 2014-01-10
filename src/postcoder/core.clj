(ns postcoder.core
  (:require [org.httpkit.client :as http]
            [clojure.string :as string]))

(def api-server "http://uk-postcodes.com")

(defn add-format [& format]
  (let [suffix (first (flatten format))
        json (str "json")]
      (if (string? suffix)
        suffix
        json)))

(defn- process-body [body suffix]
  (if (= suffix "xml")
    body
    (slurp body)))

(defn- fetch-data [path suffix]
  (let [url (string/join "." [path suffix])
        result (http/get url)]
    (process-body (:body @result) suffix)))

(defn lookup [postcode & format]
  (let [code (string/upper-case (string/replace postcode #"\s+" ""))
        path (string/join "/" [api-server "postcode" code])
        suffix (add-format format)]
    (fetch-data path suffix)))

(defn data-at-point [lat lng & format]
  (let [point (string/join "," [lat lng])
        path (string/join "/" [api-server "latlng" point])
        suffix (add-format format)]
    (fetch-data path suffix)))
