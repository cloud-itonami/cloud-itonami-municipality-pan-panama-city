# cloud-itonami-municipality-pan-panama-city

Municipal-ordinance compliance catalog for **Panama City** — the
FORTY-SEVENTH municipality-level entry, alongside 46 prior entries
including
[`cloud-itonami-municipality-prt-lisbon`](https://github.com/cloud-itonami/cloud-itonami-municipality-prt-lisbon),
[`cloud-itonami-municipality-cze-prague`](https://github.com/cloud-itonami/cloud-itonami-municipality-cze-prague),
and
[`cloud-itonami-municipality-nzl-wellington`](https://github.com/cloud-itonami/cloud-itonami-municipality-nzl-wellington).
Part of the [`cloud-itonami`](https://github.com/cloud-itonami)
compliance-fact family (ADR-2607141700,
`cloud-itonami-compliance-fact-federation`, in `com-junkawasaki/root`).

Panama's first entry across any of the 3 axes on the municipality
side — closing one of the 3 structural country-without-municipality
gaps identified at tick 141 (GTM/HND/PAN).

**13 entries, every one of them content-verified.**

## What the 2026-09-01 extension found

Two things, both by reading rather than by reasoning.

**Ley 106 de 1973 is not the standing text.** The seed catalog cited it
as the governing municipal law and stopped there. The Procuraduría de la
Administración's own norm record for Ley 106 lists the laws that have
amended it; reading them one by one gave five this catalog did not
carry — Ley 52 de 1984, Ley 37 de 2009, Ley 66 de 2015, Ley 349 de 2022
and **Ley 520 de 27 de abril de 2026**, the last of which has been in
force since 29 April 2026, *eleven weeks before the seed entry's own
`:ordinance/retrieved-at` of 2026-07-18*. A compliance catalog that
names a 1973 law and none of its amendments reports the law as it was.

**Panama City had issued nothing here.** The seed catalog carried no
instrument adopted by the city itself. Infojurídica indexes
`ACUERDO MUNICIPAL` as a norm type and the `CONCEJO MUNICIPAL DE PANAMÁ`
as an issuing authority; that pair returns 16 records. Five of them are
now in the catalog: the municipal tax regime (Acuerdo 35 de 1979), the
document fee (93 de 1968), the Gaceta Municipal (111 de 1995), the
public-markets regulation (11 de 2008) and the San Francisco partial
land-use plan (94 de 2018). The other eleven are single-purpose acts —
land purchases, street naming in Tocumen, a port-concession request —
and are **not** carried. Absence from `catalog` means no spec-basis, not
that the act does not exist.

## Verifying the citations

Every entry carries `:ordinance/url-verified-phrase`, quoted verbatim
from the document at `:ordinance/url`.

```bash
kbb --backend sci tools/verify_citations.cljk                       # all 13
kbb --backend sci tools/verify_citations.cljk --only <ordinance-id> # one
```

Exit codes are three-valued: `0` every phrase found, `1` at least one
citation is wrong, `2` **REFUSED** — the check could not be run
(no input, no `pdftotext`, nothing scanned) and makes no claim. Needs
network and poppler (`brew install poppler`).

**A status-code check could not do this job here.** Measured
2026-09-01: `norma_screen.php?numsec=99999999` — a record id that does
not exist — answers **HTTP 200** with a 12,554-byte page carrying no
`Título:` field. Eleven of thirteen citations point at that host, so a
`curl -o /dev/null -w %{http_code}` gate would be a check that cannot
fail, and a green run would be indistinguishable from no run.

Two related measurements are recorded in the source rather than
rediscovered later:

- **LEGISPAN scans are not uniformly readable.** Ley 349 de 2022's PDF
  is 8 pages whose only extractable text is the watermark *Digitalizado
  por la Asamblea Nacional*. The verifier names that case instead of
  reporting a missing phrase — detected by counting *distinct lines*,
  not bytes: the watermark extracts to ~300 characters and the Ley 106
  cover sheet, which this catalog does cite, extracts to 461. No length
  threshold separates them; eight copies of one line is not a document.
- **Two Panamanian hosts are deliberately not cited.**
  `gacetas.procuraduria-admon.gob.pa` serves the gazette PDFs over
  plaintext HTTP only (port 443 refuses connections) and every URL here
  is `https://`. `gacetaoficial.gob.pa` answers with an Incapsula
  bot-detection interstitial — this catalog does not work around bot
  detection to source a citation.

## Sourcing note

- `s3-legispan.asamblea.gob.pa` — the National Assembly's LEGISPAN
  scans, cited where the cover sheet has a text layer. It states
  Tipo/Número/Fecha/Título/Dictada por/Gaceta Oficial/Publicada el on
  one page, which is the strongest evidence available for these laws.
- `infojuridica.procuraduria-admon.gob.pa` — the Procuraduría de la
  Administración's norm database. Cited for the three laws whose
  LEGISPAN PDF could not be retrieved or has no text layer, and for all
  five Acuerdos Municipales: LEGISPAN carries National Assembly output,
  and a municipal acuerdo is not that.
- `es.wikipedia.org` — the 1519 founding only.

**A genuine date discrepancy was found and resolved** for Panama
City's 1519 founding: `en.wikipedia.org` and `es.wikipedia.org`
independently agree on 15 August 1519, while Wikidata (Q3306)'s own
"inception" statement lists 25 August 1519 — a 10-day difference. Two
independent language editions of Wikipedia agreeing with each other
against a single structured Wikidata property was treated as the
stronger signal, and the discrepancy is documented transparently
rather than silently resolved.

## Scope

A **read-only reference/archive** catalog — not an Advisor⊣Governor
actuation actor. It proposes or executes nothing on Panama City's
behalf.

Coverage is reported honestly (see `ordinance.facts/coverage`): a
municipality not in `catalog` has **no spec-basis**, full stop — never
fabricate one. The same applies within Panama City: the eleven
Acuerdos Municipales listed by Infojurídica but not carried here have
no spec-basis in this repo.

## Data

- `src/ordinance/facts.cljk` — the catalog, source of truth.
- `schema/ordinance.edn` — DataScript schema.
- `data/datascript-tx.edn` — **generated**, do not hand-edit. Regenerate
  with `kbb -M -i tools/gen_tx.cljk`; `test/ordinance/facts_test.cljk`
  fails if it drifts from the catalog. Query it alongside other
  `cloud-itonami`/`etzhayyim` compliance-fact sources via
  `com-junkawasaki/root`'s `scripts/compliance-fact-query.cljs`.
- `tools/verify_citations.cljk` — the network check described above.
- `tools/gen_tx.cljk` — the projection.

```bash
kbb -M:test    # 14 tests, offline
kbb -M:lint
```

## License

AGPL-3.0-or-later (matches the `cloud-itonami-iso3166-*` /
`-municipality-*` / `-assoc-*` / `-lei-*` convention). Law text itself
remains Panama's; this repo stores only citation metadata
(id/title/url/dates/gazette reference and one quoted phrase per
document), not full text.
