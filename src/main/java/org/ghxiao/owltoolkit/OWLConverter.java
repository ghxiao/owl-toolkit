package org.ghxiao.owltoolkit;
import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.*;
import org.semanticweb.owlapi.model.OWLDocumentFormat;
import org.semanticweb.owlapi.model.OWLOntology;

import org.semanticweb.owlapi.model.OWLOntologyManager;



public class OWLConverter {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		if (args.length != 2){
			System.err.println("Usage: OWLConverter {-rdfxml | -owlxml | -turtle | -fss | -latex | -manchester } input.owl");
			System.exit(0);
		}
		
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		
		OWLOntology ontology = manager.loadOntologyFromOntologyDocument(new File(args[1]));
		OWLDocumentFormat format = null;
		
		switch (args[0]) {
		case "-rdfxml":
			format = new RDFXMLDocumentFormat();
			break;
		case "-owlxml":
			format = new OWLXMLDocumentFormat();
			break;
		case "-turtle":
			format = new TurtleDocumentFormat();
			break;
		case "-manchester":
			format = new ManchesterSyntaxDocumentFormat();
			break;
		case "-fss":
			// workaround	for java.lang.IllegalArgumentException: Comparison method violates its general contract!
			//  at java.util.ComparableTimSort.mergeLo(ComparableTimSort.java:714)
			System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");

			format = new FunctionalSyntaxDocumentFormat();
			break;	
		case "-latex":
			format = new LatexDocumentFormat();
			break;
		default:
			throw new Exception("Unknown format: " + args[0]);
		}
		
		manager.saveOntology(ontology, format, System.out);

	}

}
