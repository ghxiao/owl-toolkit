owl-toolkit
===========

owl-toolkit is a set of command line tools for OWL files based on the owl-api library.

Download
---------
https://github.com/ghxiao/owl-toolkit/releases

Build from source
---------
1. `git clone https://github.com/ghxiao/owl-toolkit.git`
2. `cd owl-toolkit`
3. `./gradlew distZip`

Then you will find the generated zip file in the folder `build/distributions`.


Usage
----------

### owl-convert

* `owl-convert` converts the input OWL ontology to another format

```console
$ owl-convert {-rdfxml | -owlxml | -turtle | -manchester} input.owl
```

### owl-declarations

* `owl-declarations` extracts the OWL Declaration Axioms from the input ontology. 
It is very useful when user wants to disable the ontology reasoning.

```console
$ owl-declarations input.owl
```


### owl-merge

* `owl-merge` merges several OWL files into a single one

```console
$ owl-merge [-iri <iri>] file1.owl ... filen.owl
```

### owl-metrics

* `owl-metrics` prints the metrics (e.g. number of concepts/propertes/ABox assertions) of an OWL file

```console
$ owl-metrics [-v] file.owl
```

### owl-individuals

* `owl-individuals` extract OWLIndividuals from an OWL file

```console
$ owl-individuals file.owl
```

### owl-pdf

* `owl-pdf` converts the OWL file to a pdf file in DL format
  
```console
$ owl-pdf file.owl
```

### owl-profilize

* `owl-profilize` cuts the OWL file to a profile (incl. RL, EL, QL) by dropping violated axioms

```console
$ owl-profilize {-rl | -el | -ql} file.owl
```
### owl-materialize-imports

* `owl-materialize-imports` materializes the imports of the input ontology, that is, 
it replaces the `import` declaration by the concrete axioms from the imported ontologies. 

```console
$ owl-materialize-imports file.owl 
```
