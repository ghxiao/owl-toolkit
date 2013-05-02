owl-toolkit
===========

owl-toolkit is a set of command line tools for OWL files based on the
de-facto owl-api library.

Usage
----------

* `owl-convert` converts the input OWL ontology to another format

```console
$ owl-convert {-rdfxml | -owlxml | -turtle } input.owl
```

* `owl-merge` merges several OWL files into a single one

```console
$ owl-merge [-iri <iri>] file1.owl ... filen.owl
```

* `owl-metrics` prints the metrics (e.g. number of
  concepts/propertes/ABox assertions
  ) of an OWL file
  
```console
$ owl-metrics file.owl
```

