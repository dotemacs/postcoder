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

(defn lookup [postcode & format]
  (let [code (string/upper-case (string/replace postcode #"\s+" ""))
        path (string/join "/" [api-server "postcode" code])
        suffix (add-format format)
        url (string/join "." [path suffix])
        result (http/get url)
        body (:body @result)]
    (if (= suffix "xml")
      body
      (slurp body))))
