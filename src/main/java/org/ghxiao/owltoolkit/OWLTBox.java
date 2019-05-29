package org.ghxiao.owltoolkit;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.*;
import org.semanticweb.owlapi.model.OWLDocumentFormat;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.parameters.Imports;

import java.io.File;


public class OWLTBox {

	public static void main(String[] args) throws Exception {

		if (args.length != 1){
			System.err.println("Usage: OWLTBox input.owl");
			System.exit(0);
		}

		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();

		OWLOntology ontology = manager.loadOntologyFromOntologyDocument(new File(args[0]));
		OWLDocumentFormat format = new RDFXMLDocumentFormat();;

		OWLOntology tbox = manager.createOntology();

		manager.addAxioms(tbox, ontology.tboxAxioms(Imports.INCLUDED));

		manager.saveOntology(tbox, format, System.out);
	}

}
