package org.ghxiao.owltoolkit;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.RDFXMLDocumentFormat;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class OWLDeclarations {

    public static void main(String... args) throws OWLOntologyCreationException, OWLOntologyStorageException, FileNotFoundException {

        if (args.length != 2) {
            System.err.println("Usage: owl-declarations input.owl output.owl");
            System.exit(0);
        }

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(new File(args[0]));

        OWLOntology newOntology;

        Optional<IRI> ontologyIRI = ontology.getOntologyID().getOntologyIRI();

        Set<OWLDeclarationAxiom> declarationAxioms = ontology.axioms(AxiomType.DECLARATION).collect(toSet());

        // it is important remove the ontology because creating the new one with the same IRI
        manager.removeOntology(ontology);

        if (ontologyIRI.isPresent()) {
            System.err.println("Ontology: " + ontologyIRI.get());
            newOntology = manager.createOntology(ontologyIRI.get());
        } else {
            newOntology = manager.createOntology();
        }

        manager.addAxioms(newOntology, declarationAxioms.stream());

        manager.saveOntology(newOntology, new RDFXMLDocumentFormat(), new FileOutputStream(new File(args[1])));


    }

}
