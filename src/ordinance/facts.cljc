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

  ## What the 2026-09-01 extension found

  The seed catalog cited Ley N.o 106 de 1973 as the governing municipal
  law and stopped there. It is not the standing text. The Procuraduria
  de la Administracion's own norm record for Ley 106
  (infojuridica.procuraduria-admon.gob.pa/norma_screen.php?numsec=16906)
  lists the laws that have amended it, and reading them gave five
  amendments this catalog did not carry -- including Ley 520 de 27 de
  abril de 2026, in force since 29 April 2026, i.e. ELEVEN WEEKS BEFORE
  the seed entry's own :ordinance/retrieved-at of 2026-07-18. A
  compliance catalog that names a 1973 law and none of its amendments
  reports the law as it was, not as it is.

  The seed catalog also carried no instrument issued by Panama City
  itself. Infojuridica indexes ACUERDO MUNICIPAL as a norm type and the
  CONCEJO MUNICIPAL DE PANAMA as an issuing authority; the pair returns
  16 records. Five of them -- the municipal tax regime, the document fee,
  the Gaceta Municipal, the public-markets regulation, and the San
  Francisco partial land-use plan -- are now here. The remaining eleven
  are single-purpose acts (land purchases, street naming, a port
  concession request) and are not carried; absence from `catalog` means
  no spec-basis, not that the act does not exist.

  ## Every citation carries a phrase read out of the cited document

  :ordinance/url-verified-phrase is quoted verbatim from the document at
  :ordinance/url. `tools/verify_citations.cljs` refetches each URL and
  fails unless the phrase is present.

  A status-code check could not do this job on these hosts. Measured
  2026-09-01: norma_screen.php?numsec=99999999 -- a record id that does
  not exist -- returns HTTP 200 with a 12,554-byte page carrying no
  'Titulo:' field at all. A `curl -o /dev/null -w %{http_code}` gate
  against Infojuridica is a check that cannot fail, which makes a green
  run indistinguishable from no run.

  ## Sources, and why each URL is the one cited

  - s3-legispan.asamblea.gob.pa -- the National Assembly's LEGISPAN
    scans. Cited where the PDF's cover sheet is text-extractable, which
    is the strongest available evidence: it states Tipo/Numero/Fecha/
    Titulo/Dictada por/Gaceta Oficial/Publicada el on one page.
  - infojuridica.procuraduria-admon.gob.pa -- the Procuraduria de la
    Administracion's norm database. Cited for the three laws whose
    LEGISPAN PDF could not be retrieved or is a scan with no text layer
    (Ley 349 de 2022's PDF has 8 pages whose only extractable text is
    the watermark 'Digitalizado por la Asamblea Nacional'), and for all
    five Acuerdos Municipales, which LEGISPAN does not carry -- it holds
    National Assembly output, and a municipal acuerdo is not that.
  - gacetas.procuraduria-admon.gob.pa hosts the gazette PDFs and is
    NOT cited: measured 2026-09-01, it serves plaintext HTTP only
    (connection to port 443 refused), and every URL in this catalog is
    https. gacetaoficial.gob.pa is not cited either -- it answers with
    an Incapsula bot-detection interstitial, and this catalog does not
    work around bot detection to source a citation.

  The founding of Panama City in 1519 -- a GENUINE DATE DISCREPANCY
  was found and resolved: en.wikipedia.org states verbatim 'The city
  of Panama was founded on 15 August 1519, by Pedro Arias de Avila'
  and es.wikipedia.org independently states verbatim (in Spanish)
  'La ciudad fue fundada el 15 de agosto de 1519 con una poblacion de
  cien habitantes ... por el espanol de Segovia Pedro Arias Davila' --
  both language editions of Wikipedia agree on 15 August. However,
  Wikidata Q3306's own 'inception' statement lists '25 August 1519'
  instead, a 10-day discrepancy. Given TWO INDEPENDENT, directly-read
  Wikipedia-language editions agree with each other against a single
  structured Wikidata property, the Wikipedia-corroborated 15 August
  1519 date is used here, and the discrepancy is documented
  transparently rather than silently resolved. The founder's name
  (Pedro Arias Davila / Pedrarias Davila), a 16th-century historical
  figure referenced only as context for a documented founding event,
  is NOT persisted as a standalone fact.

  An ordinance not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url/date.")

(def catalog
  "municipality-slug -> vector of ordinance entries.

  `:ordinance/enacted-date` is the date the instrument itself bears --
  for the Acuerdos Municipales, the `Fecha de la norma` shown on the
  Infojuridica record, which is the date the Concejo adopted it and is
  earlier than the Gaceta publication date carried in
  `:ordinance/gazette`. `:ordinance/retrieved-at` is the day the URL was
  last fetched and `:ordinance/url-verified-phrase` confirmed present."
  {"panama-city"
   [{:ordinance/id "panama-city.ley-106-1973-regimen-municipal"
     :ordinance/title "Ley N.° 106, de 8 de octubre de 1973, Sobre Régimen Municipal -- the framework law for Panamanian municipalities. NOT the standing text on its own: amended by Ley 52 de 1984, Ley 37 de 2009, Ley 66 de 2015, Ley 349 de 2022 and Ley 520 de 2026, all of which are separate entries in this catalog"
     :ordinance/municipality "panama-city"
     :ordinance/country "PAN"
     :ordinance/kind :national-act
     :ordinance/number "Ley N.° 106 de 1973"
     :ordinance/url "https://s3-legispan.asamblea.gob.pa/legispan/NORMAS/1970/1973/LEY/Administrador%20Legispan_17458_1973_10_24_CONSEJO%20NACIONAL%20DE%20LEGISLACION_106.pdf"
     :ordinance/url-provenance :official-legispan-asamblea-gob-pa
     :ordinance/url-verified-phrase "Titulo: SOBRE REGIMEN MUNICIPAL."
     :ordinance/gazette "Gaceta Oficial 17458, publicada el 24-10-1973"
     :ordinance/enacted-date "1973-10-08"
     :ordinance/retrieved-at "2026-09-01"
     :ordinance/topic #{:governance}}

    {:ordinance/id "panama-city.founding-1519-08-15"
     :ordinance/title "Panama City founded 15 August 1519 by Pedro Arias Dávila (population of 100), corroborated by both en.wikipedia.org and es.wikipedia.org; NOTE: Wikidata Q3306's own inception statement lists 25 August 1519 instead -- the two-independent-language-Wikipedia date is used here"
     :ordinance/municipality "panama-city"
     :ordinance/country "PAN"
     :ordinance/kind :local-act
     :ordinance/number "1519"
     :ordinance/url "https://es.wikipedia.org/wiki/Ciudad_de_Panam%C3%A1"
     :ordinance/url-provenance :wikipedia-corroborated
     :ordinance/url-verified-phrase "La ciudad fue fundada el 15 de agosto de 1519 con una población de cien habitantes"
     :ordinance/enacted-date "1519-08-15"
     :ordinance/retrieved-at "2026-09-01"
     :ordinance/topic #{:governance}}

    ;; ── 2026-09-01 extension, part 1: the municipal-regime chain.
    ;; Every amendment of Ley 106 that the Procuraduría's own record for
    ;; Ley 106 lists, read one by one. Each entry below was confirmed by
    ;; fetching :ordinance/url and reading :ordinance/url-verified-phrase
    ;; out of the response.

    {:ordinance/id "panama-city.ley-105-1973-juntas-comunales"
     :ordinance/title "Ley N.° 105, de 8 de octubre de 1973 -- develops articles 224 and 225 of the Constitution, organises the Juntas Comunales and states their functions. Companion to Ley 106: the corregimiento-level tier of the same municipal regime, published in the same Gaceta"
     :ordinance/municipality "panama-city"
     :ordinance/country "PAN"
     :ordinance/kind :national-act
     :ordinance/number "Ley N.° 105 de 1973"
     :ordinance/url "https://s3-legispan.asamblea.gob.pa/legispan/NORMAS/1970/1973/LEY/Administrador%20Legispan_17458_1973_10_24_CONSEJO%20NACIONAL%20DE%20LEGISLACION_105.pdf"
     :ordinance/url-provenance :official-legispan-asamblea-gob-pa
     :ordinance/url-verified-phrase "SE ORGANIZAN LAS JUNTAS COMUNALES Y SE SEÑALAN SUS FUNCIONES"
     :ordinance/gazette "Gaceta Oficial 17458, publicada el 24-10-1973"
     :ordinance/enacted-date "1973-10-08"
     :ordinance/retrieved-at "2026-09-01"
     :ordinance/topic #{:governance}}

    {:ordinance/id "panama-city.ley-52-1984-reforma-ley-106"
     :ordinance/title "Ley N.° 52, de 12 de diciembre de 1984 -- reforms Ley 106 de 1973. Per the Procuraduría's record, it reformed Ley 106 so that each Distrito has a corporation named the Consejo Municipal, made up of the Representantes de Corregimientos elected within that Distrito"
     :ordinance/municipality "panama-city"
     :ordinance/country "PAN"
     :ordinance/kind :national-act
     :ordinance/number "Ley N.° 52 de 1984"
     :ordinance/url "https://s3-legispan.asamblea.gob.pa/legispan/NORMAS/1980/1984/LEY/Administrador%20Legispan_20214_1984_12_29_CONSEJO%20NACIONAL%20DE%20LEGISLACION_52.pdf"
     :ordinance/url-provenance :official-legispan-asamblea-gob-pa
     :ordinance/url-verified-phrase "POR LA CUAL SE REFORMA LA LEY Nº 106 DE 8 DE OCTUBRE DE 1973"
     :ordinance/gazette "Gaceta Oficial 20214, publicada el 29-12-1984"
     :ordinance/enacted-date "1984-12-12"
     :ordinance/retrieved-at "2026-09-01"
     :ordinance/topic #{:governance}}

    {:ordinance/id "panama-city.ley-37-2009-descentralizacion"
     :ordinance/title "Ley 37, de 29 de junio de 2009, Que descentraliza la Administración Pública -- develops Título VIII of the Constitution by transferring administrative, economic, political and social competences from the Executive to the municipios. Amends articles 35, 40, 65, 108 and 109 of Ley 106 de 1973"
     :ordinance/municipality "panama-city"
     :ordinance/country "PAN"
     :ordinance/kind :national-act
     :ordinance/number "Ley 37 de 2009"
     :ordinance/url "https://infojuridica.procuraduria-admon.gob.pa/norma_screen.php?numsec=41814"
     :ordinance/url-provenance :official-infojuridica-procuraduria-admon-gob-pa
     :ordinance/url-verified-phrase "QUE DESCENTRALIZA LA ADMINISTRACION PUBLICA"
     :ordinance/gazette "Gaceta Oficial 26314, publicada el 30-06-2009"
     :ordinance/enacted-date "2009-06-29"
     :ordinance/retrieved-at "2026-09-01"
     :ordinance/topic #{:governance}}

    {:ordinance/id "panama-city.ley-66-2015-reforma-ley-37"
     :ordinance/title "Ley 66, de 29 de octubre de 2015 -- reforms Ley 37 de 2009 and, per the Procuraduría's record, modifies articles 17 and 118, adds article 77-A and repeals numeral 5 of article 21 of Ley 106 de 1973; also modifies article 770 of the Código Fiscal"
     :ordinance/municipality "panama-city"
     :ordinance/country "PAN"
     :ordinance/kind :national-act
     :ordinance/number "Ley 66 de 2015"
     :ordinance/url "https://s3-legispan.asamblea.gob.pa/legispan/NORMAS/2010/2015/LEY/Administrador%20Legispan_27901-A_2015_10_30_ASAMBLEA%20NACIONAL_66.pdf"
     :ordinance/url-provenance :official-legispan-asamblea-gob-pa
     :ordinance/url-verified-phrase "QUE REFORMA LA LEY 37 DE 2009, QUE DESCENTRALIZA LA ADMINISTRACION PUBLICA"
     :ordinance/gazette "Gaceta Oficial 27901-A, publicada el 30-10-2015"
     :ordinance/enacted-date "2015-10-29"
     :ordinance/retrieved-at "2026-09-01"
     :ordinance/topic #{:governance :taxation}}

    {:ordinance/id "panama-city.ley-349-2022-contrataciones-menores"
     :ordinance/title "Ley 349, de 14 de diciembre de 2022 -- reforms Ley 106 de 1973 on minor procurement (contrataciones menores) in municipios, juntas comunales and consejos provinciales y comarcales. In force 1 January 2023"
     :ordinance/municipality "panama-city"
     :ordinance/country "PAN"
     :ordinance/kind :national-act
     :ordinance/number "Ley 349 de 2022"
     :ordinance/url "https://infojuridica.procuraduria-admon.gob.pa/norma_screen.php?numsec=59282"
     :ordinance/url-provenance :official-infojuridica-procuraduria-admon-gob-pa
     :ordinance/url-verified-phrase "RESPECTO A LAS CONTRATACIONES MENORES EN LOS MUNICIPIOS"
     :ordinance/gazette "Gaceta Oficial 29682-B, publicada el 14-12-2022"
     :ordinance/enacted-date "2022-12-14"
     :ordinance/retrieved-at "2026-09-01"
     :ordinance/topic #{:governance :procurement}}

    {:ordinance/id "panama-city.ley-520-2026-transicion-ordenada"
     :ordinance/title "Ley 520, de 27 de abril de 2026 -- reforms Ley 105 de 1973 and Ley 106 de 1973, adding a 'Deber de Transición Ordenada' (articles 68-A to 68-E of Ley 106, articles 17-A to 17-E of Ley 105) and modifying article 31 of Ley 37 de 2009. In force 29 April 2026 -- the most recent amendment to the municipal regime, and eleven weeks older than this catalog's first version"
     :ordinance/municipality "panama-city"
     :ordinance/country "PAN"
     :ordinance/kind :national-act
     :ordinance/number "Ley 520 de 2026"
     :ordinance/url "https://infojuridica.procuraduria-admon.gob.pa/norma_screen.php?numsec=67360"
     :ordinance/url-provenance :official-infojuridica-procuraduria-admon-gob-pa
     :ordinance/url-verified-phrase "QUE REFORMA LA LEY 105 DE 1973, QUE ORGANIZA LAS JUNTAS COMUNALES, Y LA LEY 106 DE 1973"
     :ordinance/gazette "Gaceta Oficial 30513, publicada el 28-04-2026"
     :ordinance/enacted-date "2026-04-27"
     :ordinance/retrieved-at "2026-09-01"
     :ordinance/topic #{:governance}}

    ;; ── 2026-09-01 extension, part 2: instruments issued by Panama
    ;; City itself. Infojurídica's norm type ACUERDO MUNICIPAL, filtered
    ;; to the CONCEJO MUNICIPAL DE PANAMÁ, returns 16 records; these are
    ;; the five with standing municipal effect.

    {:ordinance/id "panama-city.acuerdo-35-1979-regimen-impositivo"
     :ordinance/title "Acuerdo Municipal N.° 35, de 11 de diciembre de 1979 -- establishes the municipality's tax regime (nuevo régimen impositivo del Municipio). The Concejo Municipal de Panamá's own revenue instrument, adopted under the taxing power Ley 106 de 1973 confers"
     :ordinance/municipality "panama-city"
     :ordinance/country "PAN"
     :ordinance/kind :municipal-regulation
     :ordinance/number "Acuerdo N.° 35 de 1979"
     :ordinance/url "https://infojuridica.procuraduria-admon.gob.pa/norma_screen.php?numsec=18039"
     :ordinance/url-provenance :official-infojuridica-procuraduria-admon-gob-pa
     :ordinance/url-verified-phrase "POR EL CUAL SE ESTABLECE EL NUEVO REGIMEN IMPOSITIVO DEL MUNICIPIO"
     :ordinance/gazette "Gaceta Oficial 18981, publicada el 04-01-1980"
     :ordinance/enacted-date "1979-12-11"
     :ordinance/retrieved-at "2026-09-01"
     :ordinance/topic #{:taxation}}

    {:ordinance/id "panama-city.acuerdo-93-1968-tasa-documentos"
     :ordinance/title "Acuerdo Municipal N.° 93, de 23 de diciembre de 1968 -- creates an administration fee (tasa de administración) on documents issued by municipal authorities at a party's request"
     :ordinance/municipality "panama-city"
     :ordinance/country "PAN"
     :ordinance/kind :municipal-regulation
     :ordinance/number "Acuerdo N.° 93 de 1968"
     :ordinance/url "https://infojuridica.procuraduria-admon.gob.pa/norma_screen.php?numsec=14453"
     :ordinance/url-provenance :official-infojuridica-procuraduria-admon-gob-pa
     :ordinance/url-verified-phrase "POR EL CUAL SE CREA UNA TASA DE ADMINISTRACION POR LOS DOCUMENTOS QUE EXPIDAN LAS AUTORIDADES MUNICIPALES"
     :ordinance/gazette "Gaceta Oficial 16326, publicada el 25-03-1969"
     :ordinance/enacted-date "1968-12-23"
     :ordinance/retrieved-at "2026-09-01"
     :ordinance/topic #{:taxation}}

    {:ordinance/id "panama-city.acuerdo-111-1995-gaceta-municipal"
     :ordinance/title "Acuerdo Municipal N.° 111, de 1 de agosto de 1995 -- creates the Gaceta Municipal and regulates its operation. The municipality's own publication vehicle: the instrument that determines where a Panama City acuerdo is published and therefore when it can be relied on"
     :ordinance/municipality "panama-city"
     :ordinance/country "PAN"
     :ordinance/kind :municipal-regulation
     :ordinance/number "Acuerdo N.° 111 de 1995"
     :ordinance/url "https://infojuridica.procuraduria-admon.gob.pa/norma_screen.php?numsec=26867"
     :ordinance/url-provenance :official-infojuridica-procuraduria-admon-gob-pa
     :ordinance/url-verified-phrase "POR MEDIO DEL CUAL SE CREA LA GACETA MUNICIPAL Y SE REGULA SU FUNCIONAMIENTO"
     :ordinance/gazette "Gaceta Oficial 22849, publicada el 17-08-1995"
     :ordinance/enacted-date "1995-08-01"
     :ordinance/retrieved-at "2026-09-01"
     :ordinance/topic #{:governance :information-disclosure :transparency}}

    {:ordinance/id "panama-city.acuerdo-11-2008-mercados-publicos"
     :ordinance/title "Acuerdo Municipal N.° 11, de 29 de enero de 2008 -- the Concejo Municipal de Panamá regulates all activity within the municipal public markets and adopts other measures and procedures"
     :ordinance/municipality "panama-city"
     :ordinance/country "PAN"
     :ordinance/kind :municipal-regulation
     :ordinance/number "Acuerdo N.° 11 de 2008"
     :ordinance/url "https://infojuridica.procuraduria-admon.gob.pa/norma_screen.php?numsec=38714"
     :ordinance/url-provenance :official-infojuridica-procuraduria-admon-gob-pa
     :ordinance/url-verified-phrase "REGLAMENTA TODAS LAS ACTIVIDADES DENTRO DE LOS MERCADOS PUBLICOS MUNICIPALES"
     :ordinance/gazette "Gaceta Oficial 25987, publicada el 27-02-2008"
     :ordinance/enacted-date "2008-01-29"
     :ordinance/retrieved-at "2026-09-01"
     :ordinance/topic #{:licensing :public-markets}}

    {:ordinance/id "panama-city.acuerdo-94-2018-pot-san-francisco"
     :ordinance/title "Acuerdo Municipal N.° 94, de 4 de abril de 2018 -- approves the Plan Parcial de Ordenamiento Territorial for the corregimiento of San Francisco, Distrito y Provincia de Panamá. Binding land-use planning for one corregimiento, not the whole distrito"
     :ordinance/municipality "panama-city"
     :ordinance/country "PAN"
     :ordinance/kind :municipal-plan
     :ordinance/number "Acuerdo N.° 94 de 2018"
     :ordinance/url "https://infojuridica.procuraduria-admon.gob.pa/norma_screen.php?numsec=52094"
     :ordinance/url-provenance :official-infojuridica-procuraduria-admon-gob-pa
     :ordinance/url-verified-phrase "SE APRUEBA EL PLAN PARCIAL DE ORDENAMIENTO TERRITORIAL DEL CORREGIMIENTO DE SAN FRANCISCO"
     :ordinance/gazette "Gaceta Oficial 28524-A, publicada el 14-05-2018"
     :ordinance/enacted-date "2018-04-04"
     :ordinance/retrieved-at "2026-09-01"
     :ordinance/topic #{:urban-planning :zoning}}]})

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
      :note (str "cloud-itonami-municipality-pan-panama-city (ADR-2607141700): "
                 (count (get catalog "panama-city")) " Panama City entries, each "
                 "with a verbatim phrase confirmed against the cited document "
                 "(see tools/verify_citations.cljs). "
                 "Extend `ordinance.facts/catalog`, never fabricate an id/url.")})))

(defn by-topic [muni topic]
  (filterv #(contains? (:ordinance/topic %) topic) (spec-basis muni)))

(defn citations
  "Every entry's (id, url, phrase) triple -- the input to the citation
  verifier. An entry with no phrase is reported with `nil` rather than
  omitted, so that an unverifiable entry cannot hide by being absent."
  ([] (citations "panama-city"))
  ([muni]
   (mapv (fn [o] {:id (:ordinance/id o)
                  :url (:ordinance/url o)
                  :phrase (:ordinance/url-verified-phrase o)})
         (spec-basis muni))))
