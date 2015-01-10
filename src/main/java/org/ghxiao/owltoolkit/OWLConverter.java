package org.ghxiao.owltoolkit;
import java.io.File;

import org.coode.owlapi.latex.LatexOntologyFormat;
import org.coode.owlapi.turtle.TurtleOntologyFormat;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLFunctionalSyntaxOntologyFormat;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.io.RDFXMLOntologyFormat;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyFormat;
import org.semanticweb.owlapi.model.OWLOntologyManager;



public class OWLConverter {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		if (args.length != 2){
			System.err.println("Usage: OWLConverter {-rdfxml | -owlxml | -turtle | -fss | -latex } input.owl");
			System.exit(0);
		}
		
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		
		OWLOntology ontology = manager.loadOntologyFromOntologyDocument(new File(args[1]));
		OWLOntologyFormat format = null;
		
		switch (args[0]) {
		case "-rdfxml":
			format = new RDFXMLOntologyFormat();
			break;
		case "-owlxml":
			format = new OWLXMLOntologyFormat();
			break;
		case "-turtle":
			format = new TurtleOntologyFormat();
			break;
		case "-fss":
			// workaround	for java.lang.IllegalArgumentException: Comparison method violates its general contract!
			//  at java.util.ComparableTimSort.mergeLo(ComparableTimSort.java:714)
			System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");

			format = new OWLFunctionalSyntaxOntologyFormat();
			break;	
		case "-latex":
			format = new LatexOntologyFormat();
			break;
		default:
			throw new Exception("Unknown format: " + args[0]);
		}
		
		manager.saveOntology(ontology, format, System.out);

	}

}
