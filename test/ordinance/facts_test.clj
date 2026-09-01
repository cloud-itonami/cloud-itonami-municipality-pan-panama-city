(ns ordinance.facts-test
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.set :as set]
            [clojure.string :as str]
            [clojure.test :refer [deftest is testing]]
            [ordinance.facts :as facts]))

(def entries (facts/spec-basis "panama-city"))

(deftest panama-city-has-spec-basis
  (is (= 13 (count entries)))
  (is (every? #(str/starts-with? (:ordinance/url %) "https://") entries))
  (is (every? :ordinance/number entries)))

(deftest unknown-municipality-has-no-spec-basis
  (is (nil? (facts/spec-basis "colon")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["panama-city" "colon"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["colon"] (:missing-municipalities c)))))

(deftest by-topic-filters
  (is (= 9 (count (facts/by-topic "panama-city" :governance))))
  (is (= 3 (count (facts/by-topic "panama-city" :taxation))))
  (is (= 1 (count (facts/by-topic "panama-city" :urban-planning))))
  (is (empty? (facts/by-topic "panama-city" :labor)))
  (is (empty? (facts/by-topic "colon" :governance))))

(deftest ids-are-unique
  (is (= (count entries) (count (set (map :ordinance/id entries))))))

(deftest every-citation-carries-a-verbatim-phrase
  ;; The phrase is what `tools/verify_citations.cljs` looks for in the
  ;; fetched document. An entry without one is UNVERIFIED, and this
  ;; catalog does not carry unverified citations.
  (doseq [{:ordinance/keys [id url-verified-phrase]} entries]
    (is (not (str/blank? url-verified-phrase))
        (str id " has no :ordinance/url-verified-phrase"))
    ;; A one-word phrase would match almost any document; it would pass
    ;; without discriminating.
    (is (<= 12 (count (or url-verified-phrase "")))
        (str id "'s phrase is too short to identify a document"))))

(deftest urls-are-distinct
  ;; :ingest/citation-count in the fleet maturity scan counts `https://`
  ;; occurrences, so repeating one URL would raise the score without
  ;; adding a source. Two ordinances must not share a URL.
  (let [urls (map :ordinance/url entries)
        dupes (->> urls frequencies (filter (fn [[_ n]] (< 1 n))) (map key))]
    (is (empty? dupes) (str "duplicate :ordinance/url: " (pr-str dupes)))))

(deftest phrases-are-distinct
  ;; Two entries sharing a phrase would both go green off one document.
  (let [ps (map :ordinance/url-verified-phrase entries)
        dupes (->> ps frequencies (filter (fn [[_ n]] (< 1 n))) (map key))]
    (is (empty? dupes) (str "duplicate :ordinance/url-verified-phrase: " (pr-str dupes)))))

(deftest citations-reports-every-entry
  (is (= (count entries) (count (facts/citations))))
  (is (= (set (map :ordinance/id entries))
         (set (map :id (facts/citations))))))

(deftest dates-are-iso
  (doseq [{:ordinance/keys [id enacted-date retrieved-at]} entries]
    (is (re-matches #"\d{4}-\d{2}-\d{2}" enacted-date) (str id " enacted-date"))
    (is (re-matches #"\d{4}-\d{2}-\d{2}" retrieved-at) (str id " retrieved-at"))))

(deftest gazette-precedes-nothing-impossible
  ;; A Panamanian acuerdo is adopted by the Concejo and published in the
  ;; Gaceta Oficial afterwards, so the enactment date cannot be later than
  ;; the publication date recorded in :ordinance/gazette. Transcribing a
  ;; dd-mm-yyyy gazette date into the wrong field, or copying a neighbour's
  ;; line, shows up here.
  (doseq [{:ordinance/keys [id enacted-date gazette]} entries
          :when (not (str/blank? gazette))]
    (let [[_ d m y] (re-find #"publicada el (\d{2})-(\d{2})-(\d{4})" gazette)]
      (is (some? y) (str id "'s :ordinance/gazette has no `publicada el dd-mm-yyyy`: " (pr-str gazette)))
      (when y
        (is (<= (compare enacted-date (str y "-" m "-" d)) 0)
            (str id " is dated " enacted-date " but its gazette says it was published earlier, on "
                 y "-" m "-" d))))))

(deftest tx-data-is-derived-from-the-catalog
  ;; data/datascript-tx.edn is a projection of `catalog` (regenerate with
  ;; `clojure -M -i tools/gen_tx.clj`). Before 2026-09-01 there was no
  ;; generator and the two were kept in step by hand. If the file is
  ;; hand-edited, or the catalog moves without regenerating, a consumer
  ;; querying the tx-data sees something this repo never asserted.
  (let [f (io/file "data/datascript-tx.edn")]
    (is (.exists f) "data/datascript-tx.edn is missing")
    (let [rows (edn/read-string (slurp f))
          norm (fn [o] (update o :ordinance/topic #(vec (sort %))))]
      (is (= (count entries) (count rows)))
      (is (= (mapv norm entries) (mapv norm rows))))))

(deftest schema-declares-every-attribute-used
  (let [schema (edn/read-string (slurp (io/file "schema/ordinance.edn")))
        used (into #{} (mapcat keys entries))
        undeclared (set/difference used (set (keys schema)))]
    (is (empty? undeclared)
        (str "attributes used by the catalog but absent from schema/ordinance.edn: "
             (pr-str undeclared)))))

(deftest provenance-is-recorded
  (testing "every entry says where its URL came from"
    (is (every? :ordinance/url-provenance entries)))
  (testing "the provenance keyword names the host the URL actually points at"
    ;; Measured 2026-09-01: this catalog draws on three hosts, and a
    ;; copy-pasted entry that keeps the neighbour's provenance while
    ;; changing the URL is invisible to every other check here.
    (let [host->prov {"s3-legispan.asamblea.gob.pa" :official-legispan-asamblea-gob-pa
                      "infojuridica.procuraduria-admon.gob.pa" :official-infojuridica-procuraduria-admon-gob-pa
                      "es.wikipedia.org" :wikipedia-corroborated}]
      (doseq [{:ordinance/keys [id url url-provenance]} entries]
        (let [host (second (re-find #"^https://([^/]+)/" url))]
          (is (contains? host->prov host)
              (str id " cites " (pr-str host) ", which this test does not know"))
          (is (= (host->prov host) url-provenance)
              (str id " cites " host " but declares provenance " url-provenance))))))
  (testing "gazette-published instruments record the publication reference"
    ;; The Wikipedia-corroborated founding entry has no gazette; every
    ;; instrument published in the Gaceta Oficial does.
    (doseq [{:ordinance/keys [id url-provenance gazette]} entries
            :when (not= :wikipedia-corroborated url-provenance)]
      (is (not (str/blank? gazette)) (str id " has no :ordinance/gazette")))))
