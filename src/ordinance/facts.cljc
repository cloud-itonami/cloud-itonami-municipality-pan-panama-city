(ns ordinance.facts
  "Municipal-ordinance compliance catalog for Panama City -- the
  FORTY-SEVENTH municipality-level entry (see
  cloud-itonami-municipality-jpn-tokyo, -usa-washington-dc, -gbr-london,
  -can-toronto, -deu-berlin, -fra-paris, -nld-amsterdam, -esp-madrid,
  -kor-seoul, -ita-roma, -aus-sydney, -arg-buenos-aires, -fin-helsinki,
  -dnk-copenhagen, -nor-oslo, -bel-brussels, -chl-santiago, -col-bogota,
  -cri-san-jose, -bra-sao-paulo, -ury-montevideo, -zaf-cape-town,
  -ecu-quito, -swe-gothenburg, -pry-asuncion, -mex-guadalajara,
  -fra-lyon, -ind-new-delhi, -pol-warsaw, -ken-nairobi, -tha-bangkok,
  -are-abu-dhabi, -vnm-hanoi, -idn-jakarta, -phl-manila, -egy-cairo,
  -tur-ankara, -nga-abuja, -sau-riyadh, -mys-kuala-lumpur, -aut-vienna,
  -che-bern, -irl-dublin, -nzl-wellington, -cze-prague, -prt-lisbon for
  the first forty-six) per ADR-2607141700 (cloud-itonami-compliance-
  fact-federation). Panama's first entry across the municipality axis,
  closing one of the 3 remaining structural country-without-municipality
  gaps identified at tick 141 (GTM/HND/PAN).

  Panama City is Panama's stable capital (Wikidata Q3306), with no
  ongoing ambiguity.

  Ley N.° 106 (de 8 de octubre de 1973), 'Sobre Régimen Municipal' --
  title, law number, and enactment date directly confirmed by reading
  the official Panamanian National Assembly (Asamblea Legislativa,
  LEGISPAN system) cover-sheet PDF via the Read-tool saved-path
  fallback (WebFetch itself reported the PDF as illegible/binary),
  which shows verbatim: 'Tipo de Norma: LEY', 'Número: 106', 'Año:
  1973', 'Fecha (dd-mm-aaaa): 08-10-1973', 'Titulo: SOBRE REGIMEN
  MUNICIPAL', 'Dictada por: CONSEJO NACIONAL DE LEGISLACION',
  'Gaceta Oficial: 17458', 'Publicada el: 24-10-1973'.

  The founding of Panama City in 1519 -- a GENUINE DATE DISCREPANCY
  was found and resolved: en.wikipedia.org states verbatim 'The city
  of Panama was founded on 15 August 1519, by Pedro Arias de Ávila'
  and es.wikipedia.org independently states verbatim (in Spanish)
  'La ciudad fue fundada el 15 de agosto de 1519 con una población de
  cien habitantes ... por el español de Segovia Pedro Arias Dávila' --
  both language editions of Wikipedia agree on 15 August. However,
  Wikidata Q3306's own 'inception' statement lists '25 August 1519'
  instead, a 10-day discrepancy. Given TWO INDEPENDENT, directly-read
  Wikipedia-language editions agree with each other against a single
  structured Wikidata property, the Wikipedia-corroborated 15 August
  1519 date is used here, and the discrepancy is documented
  transparently rather than silently resolved. The founder's name
  (Pedro Arias Dávila / Pedrarias Dávila), a 16th-century historical
  figure referenced only as context for a documented founding event,
  is NOT persisted as a standalone fact.

  An ordinance not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url/date.")

(def catalog
  "municipality-slug -> vector of ordinance entries."
  {"panama-city"
   [{:ordinance/id "panama-city.ley-106-1973-regimen-municipal"
     :ordinance/title "Ley N.° 106, de 8 de octubre de 1973, Sobre Régimen Municipal"
     :ordinance/municipality "panama-city"
     :ordinance/country "PAN"
     :ordinance/kind :local-act
     :ordinance/number "Ley N.° 106 de 1973"
     :ordinance/url "https://s3-legispan.asamblea.gob.pa/legispan/NORMAS/1970/1973/LEY/Administrador%20Legispan_17458_1973_10_24_CONSEJO%20NACIONAL%20DE%20LEGISLACION_106.pdf"
     :ordinance/url-provenance :official-legispan-asamblea-gob-pa
     :ordinance/enacted-date "1973-10-08"
     :ordinance/retrieved-at "2026-07-18"
     :ordinance/topic #{:governance}}
    {:ordinance/id "panama-city.founding-1519-08-15"
     :ordinance/title "Panama City founded 15 August 1519 by Pedro Arias Dávila (population of 100), corroborated by both en.wikipedia.org and es.wikipedia.org; NOTE: Wikidata Q3306's own inception statement lists 25 August 1519 instead -- the two-independent-language-Wikipedia date is used here"
     :ordinance/municipality "panama-city"
     :ordinance/country "PAN"
     :ordinance/kind :local-act
     :ordinance/number "1519"
     :ordinance/url "https://es.wikipedia.org/wiki/Ciudad_de_Panam%C3%A1"
     :ordinance/url-provenance :wikipedia-corroborated
     :ordinance/enacted-date "1519-08-15"
     :ordinance/retrieved-at "2026-07-18"
     :ordinance/topic #{:governance}}]})

(defn spec-basis [muni] (get catalog muni))

(defn coverage
  ([] (coverage (keys catalog)))
  ([munis]
   (let [have (filter catalog munis)
         missing (remove catalog munis)]
     {:requested (count munis)
      :covered (count have)
      :covered-municipalities (vec (sort have))
      :missing-municipalities (vec (sort missing))
      :note (str "cloud-itonami-municipality-pan-panama-city Wave 0 (ADR-2607141700): "
                 (count (get catalog "panama-city")) " Panama City entries seeded "
                 "with LEGISPAN/Wikipedia citations. "
                 "Extend `ordinance.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [muni topic]
  (filterv #(contains? (:ordinance/topic %) topic) (spec-basis muni)))
