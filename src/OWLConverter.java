import java.io.File;

import org.coode.owlapi.turtle.TurtleOntologyFormat;
import org.semanticweb.owlapi.apibinding.OWLManager;
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
			System.err.println("Usage: OWLConverter {-rdfxml | -owlxml | -turtle } input.owl");
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
		default:
			throw new Exception("Unknown format: " + args[0]);
		}
		
		manager.saveOntology(ontology, format, System.out);

	}

}
