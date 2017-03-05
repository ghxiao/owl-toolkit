package org.ghxiao.owltoolkit;
import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class OWLObjectProperties {

	/**
	 * @param args
	 * @throws OWLOntologyCreationException
	 */
	public static void main(String... args) throws OWLOntologyCreationException {
		
		if (args.length != 1){
			System.err.println("Usage: owl-objectproperties input.owl");
			System.exit(0);
		}
		
		OWLOntology ontology = OWLManager.createOWLOntologyManager()
				.loadOntologyFromOntologyDocument(new File(args[0]));

		System.err.println("Ontology "
				+ ontology.getOntologyID().getOntologyIRI());

        ontology.objectPropertiesInSignature().forEach(System.out::println);

	}

}
