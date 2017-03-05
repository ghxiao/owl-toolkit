package org.ghxiao.owltoolkit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;

import org.semanticweb.owlapi.formats.RDFXMLDocumentFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

public class OWLMerger {

    /**
     * @param args
     * @throws OWLOntologyCreationException
     * @throws OWLOntologyStorageException
     * @throws FileNotFoundException
     */
    public static void main(String... args)
            throws OWLOntologyCreationException, OWLOntologyStorageException,
            FileNotFoundException {
        if (args.length == 0) {
            System.err.println("Usage: owl-merge file1.owl ... filen.owl");
            System.exit(0);
        }

        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");

        Set<OWLAxiom> axioms = new HashSet<>();
        Optional<IRI> iri = null;
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        for (String ontologyFile : args) {
            try {
                OWLOntologyManager manager1 = OWLManager.createOWLOntologyManager();
                OWLOntology ontology = manager1
                        .loadOntologyFromOntologyDocument(new File(ontologyFile));
                iri = ontology.getOntologyID().getOntologyIRI();
                axioms.addAll(ontology.getAxioms());
            } catch (OWLOntologyCreationException e) {
                e.printStackTrace();
            }
        }

        // OWLOntology merged = manager.createOntology(ontologies.iterator()
        // .next().getOntologyID().getOntologyIRI());

        OWLOntology merged;

        if (iri != null && iri.isPresent()) {
            merged = manager.createOntology(axioms, iri.get());
        } else {
            merged = manager.createOntology(axioms);
        }

        manager.saveOntology(merged, new RDFXMLDocumentFormat(), System.out);

    }

}
