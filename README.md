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

## Sourcing note

The current governing law (Ley N.° 106, de 8 de octubre de 1973,
"Sobre Régimen Municipal") is directly confirmed by reading the
official Panamanian National Assembly's own LEGISPAN cover-sheet PDF
via the Read-tool saved-path fallback (WebFetch itself reported the
PDF as illegible/binary).

**A genuine date discrepancy was found and resolved**: for Panama
City's 1519 founding, `en.wikipedia.org` and `es.wikipedia.org`
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
fabricate one.

## Data

- `src/ordinance/facts.cljc` — the catalog, source of truth.
- `schema/ordinance.edn` — DataScript schema.
- `data/datascript-tx.edn` — derived DataScript tx-data (query this
  alongside other `cloud-itonami`/`etzhayyim` compliance-fact sources via
  `com-junkawasaki/root`'s `scripts/compliance-fact-query.cljs`).

Both entries directly confirmed: **Ley N.° 106 de 1973** (Sobre
Régimen Municipal, 8 October 1973) and the **founding of Panama
City** on 15 August 1519 by Pedro Arias Dávila.

## License

AGPL-3.0-or-later (matches the `cloud-itonami-iso3166-*` /
`-municipality-*` / `-assoc-*` / `-lei-*` convention). Law text itself
remains Panama's; this repo stores only citation metadata
(id/title/url/dates), not full text.
