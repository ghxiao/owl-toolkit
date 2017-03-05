package org.ghxiao.owltoolkit;

import java.io.File;
import java.util.Optional;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class OWLClasses {

    public static void main(String... args) throws OWLOntologyCreationException {

        if (args.length != 1) {
            System.err.println("Usage: owl-classes input.owl");
            System.exit(0);
        }

        OWLOntology ontology = OWLManager.createOWLOntologyManager()
                .loadOntologyFromOntologyDocument(new File(args[0]));

        final Optional<IRI> ontologyIRI = ontology.getOntologyID().getOntologyIRI();

        ontologyIRI.ifPresent(iri -> System.err.println("Ontology: " + iri));

        ontology.classesInSignature().forEach(System.out::println);
    }

}
