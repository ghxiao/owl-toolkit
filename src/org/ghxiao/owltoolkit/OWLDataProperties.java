package org.ghxiao.owltoolkit;
import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class OWLDataProperties {

	/**
	 * @param args
	 * @throws OWLOntologyCreationException
	 */
	public static void main(String... args) throws OWLOntologyCreationException {
		if (args.length != 1){
			System.err.println("Usage: owl-dataproperties input.owl");
			System.exit(0);
		}
		
		OWLOntology ontology = OWLManager.createOWLOntologyManager()
				.loadOntologyFromOntologyDocument(new File(args[0]));

		System.err.println("Ontology "
				+ ontology.getOntologyID().getOntologyIRI());

		for (OWLDataProperty property : ontology
				.getDataPropertiesInSignature()) {
			System.out.println(property);
		}
	}

}
