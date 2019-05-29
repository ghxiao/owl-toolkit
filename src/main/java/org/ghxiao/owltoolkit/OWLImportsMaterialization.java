package org.ghxiao.owltoolkit;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.protege.xmlcatalog.owlapi.XMLCatalogIRIMapper;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

public class OWLImportsMaterialization {
    public static void main(String... args)
            throws OWLOntologyCreationException, OWLOntologyStorageException, IOException {
        if (args.length != 1 && args.length != 2) {
            printUsage();
            System.exit(0);
        }

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();

        if (args.length == 2) {
            XMLCatalogIRIMapper iriMapper = new XMLCatalogIRIMapper(new File(args[1]));
            manager.setIRIMappers(new HashSet<>(Collections.singletonList(iriMapper)));
        }
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(new File(args[0]));

        OWLOntologyManager manager1 = OWLManager.createOWLOntologyManager();
        OWLOntology materializedOntology = manager1.createOntology(ontology.getOntologyID());

        final Stream<OWLAxiom> axioms = ontology.importsClosure().flatMap(HasAxioms::axioms);
        manager1.addAxioms(materializedOntology, axioms);

        manager1.saveOntology(materializedOntology, System.out);
    }

    private static void printUsage() {
        System.err.println("Usage: owl-materialize-imports file.owl [catalog-v001.xml]");
    }
}

