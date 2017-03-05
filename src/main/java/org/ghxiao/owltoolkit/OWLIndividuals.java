package org.ghxiao.owltoolkit;
import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class OWLIndividuals {

	public static void main(String... args) throws OWLOntologyCreationException {
		if (args.length != 1){
			System.err.println("Usage: owl-individuals input.owl");
			System.exit(0);
		}
		
		OWLOntology ontology = OWLManager.createOWLOntologyManager()
				.loadOntologyFromOntologyDocument(new File(args[0]));
		
		System.err.println("Ontology " + ontology.getOntologyID().getOntologyIRI());

		ontology.individualsInSignature().forEach(System.out::println);

	}

}
