(ns postcoder.core-test
  (:require [clojure.test :refer :all]
            [postcoder.core :refer :all]))

(deftest return-formats
  (testing "JSON"
    (let [correct-json "{\"postcode\":\"SW1A 1AA\",\"geo\":{\"lat\":51.5010091744138,\"lng\":-0.14157319687256223,\"easting\":529090.0,\"northing\":179645.0,\"geohash\":\"http://geohash.org/gcpuuz2zj5gq\"},\"administrative\":{\"council\":{\"title\":\"City of Westminster\",\"uri\":\"http://statistics.data.gov.uk/id/statistical-geography/E09000033\",\"code\":\"E09000033\"},\"ward\":{\"title\":\"St. James's\",\"uri\":\"http://statistics.data.gov.uk/id/statistical-geography/E05000644\",\"code\":\"E05000644\"},\"constituency\":{\"title\":\"Cities of London and Westminster\",\"uri\":\"http://statistics.data.gov.uk/id/statistical-geography/E14000639\",\"code\":\"E14000639\"}}}"]
      (is (= correct-json (lookup "sw1a1aa" "json")))))
  (testing "XML"
    (let [correct-xml "<result>\n  <postcode>SW1A 1AA</postcode>\n  <geo>\n    <lat>51.5010091744138</lat>\n    <lng>-0.14157319687256223</lng>\n    <easting>529090.0</easting>\n    <northing>179645.0</northing>\n    <geohash>http://geohash.org/gcpuuz2zj5gq</geohash>\n  </geo>\n  <administrative>\n    <council>\n      <title>City of Westminster</title>\n      <uri>http://statistics.data.gov.uk/id/statistical-geography/E09000033</uri>\n      <code>E09000033</code>\n    </council>\n    <ward>\n      <title>St. James's</title>\n      <uri>http://statistics.data.gov.uk/id/statistical-geography/E05000644</uri>\n      <code>E05000644</code>\n    </ward>\n    <constituency>\n      <title>Cities of London and Westminster</title>\n      <uri>http://statistics.data.gov.uk/id/statistical-geography/E14000639</uri>\n      <code>E14000639</code>\n    </constituency>\n  </administrative>\n</result>\n"]
      (is (= correct-xml (lookup "sw1a1aa" "xml")))))
  (testing "CSV"
    (let [correct-csv "SW1A 1AA,51.5010091744138,-0.14157319687256223,529090.0,179645.0,http://geohash.org/gcpuuz2zj5gq,,,E09000033,City of Westminster,E05000644,St. James's\n"]
      (is (= correct-csv (lookup "sw1a1aa" "csv")))))
  (testing "RDF"
    (let [correct-rdf "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\" xmlns:ns0=\"http://data.ordnancesurvey.co.uk/ontology/postcode/\" xmlns:spatialrelations=\"http://data.ordnancesurvey.co.uk/ontology/spatialrelations/\" xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\" xmlns:owl=\"http://www.w3.org/2002/07/owl#\" xmlns:geo=\"http://www.w3.org/2003/01/geo/wgs84_pos#\">\n  <ns0:PostcodeUnit rdf:about=\"http://uk-postcodes.com/postcode/SW1A1AA\">\n    <spatialrelations:LondonBorough>\n      <rdf:Description rdf:about=\"http://data.ordnancesurvey.co.uk/id/7000000000011164\">\n        <rdfs:label>City of Westminster</rdfs:label>\n      </rdf:Description>\n    </spatialrelations:LondonBorough>\n    <spatialrelations:LondonBoroughWard>\n      <rdf:Description rdf:about=\"http://data.ordnancesurvey.co.uk/id/7000000000011162\">\n        <rdfs:label>St. James's</rdfs:label>\n      </rdf:Description>\n    </spatialrelations:LondonBoroughWard>\n    <spatialrelations:WestminsterConstituency>\n      <rdf:Description rdf:about=\"http://data.ordnancesurvey.co.uk/id/7000000000025040\">\n        <rdfs:label>Cities of London and Westminster</rdfs:label>\n      </rdf:Description>\n    </spatialrelations:WestminsterConstituency>\n    <spatialrelations:easting rdf:datatype=\"http://www.w3.org/2001/XMLSchema#integer\">529090</spatialrelations:easting>\n    <spatialrelations:northing rdf:datatype=\"http://www.w3.org/2001/XMLSchema#integer\">179645</spatialrelations:northing>\n    <spatialrelations:t_spatiallyInside rdf:resource=\"http://statistics.data.gov.uk/id/statistical-geography/E05000644\"/>\n    <spatialrelations:t_spatiallyInside rdf:resource=\"http://statistics.data.gov.uk/id/statistical-geography/E09000033\"/>\n    <spatialrelations:t_spatiallyInside rdf:resource=\"http://statistics.data.gov.uk/id/statistical-geography/E14000639\"/>\n    <rdfs:label>SW1A 1AA</rdfs:label>\n    <owl:sameAs rdf:resource=\"http://data.ordnancesurvey.co.uk/id/postcodeunit/SW1A1AA\"/>\n    <geo:lat rdf:datatype=\"http://www.w3.org/2001/XMLSchema#decimal\">51.5010091744138</geo:lat>\n    <geo:long rdf:datatype=\"http://www.w3.org/2001/XMLSchema#decimal\">-0.14157319687256223</geo:long>\n  </ns0:PostcodeUnit>\n</rdf:RDF>\n"]
      (is (= correct-rdf  (lookup "sw1a1aa" "rdf"))))))

(deftest data-at-coordinates
  (testing "JSON"
    (let [correct-json "{\"postcode\":\"SW1A 1AA\",\"geo\":{\"lat\":51.5010091744138,\"lng\":-0.14157319687256223,\"easting\":529090.0,\"northing\":179645.0,\"geohash\":\"http://geohash.org/gcpuuz2zj5gq\"},\"administrative\":{\"council\":{\"title\":\"City of Westminster\",\"uri\":\"http://statistics.data.gov.uk/id/statistical-geography/E09000033\",\"code\":\"E09000033\"},\"ward\":{\"title\":\"St. James's\",\"uri\":\"http://statistics.data.gov.uk/id/statistical-geography/E05000644\",\"code\":\"E05000644\"},\"constituency\":{\"title\":\"Cities of London and Westminster\",\"uri\":\"http://statistics.data.gov.uk/id/statistical-geography/E14000639\",\"code\":\"E14000639\"}}}"]
      (is (= correct-json (data-at-point "51.5010091744138" "-0.14157319687256223" "json"))))))
