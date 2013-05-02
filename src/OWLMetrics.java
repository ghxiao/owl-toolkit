import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;


public class OWLMetrics {

	/**
	 * @param args
	 * @throws OWLOntologyCreationException 
	 */
	public static void main(String... args) throws OWLOntologyCreationException {
		OWLOntology ontology = OWLManager.createOWLOntologyManager().loadOntologyFromOntologyDocument(new File(args[0]));
		Map<String, Object> metrics = new LinkedHashMap<>();
		metrics.put("Ontology", ontology.getOntologyID().getOntologyIRI());
		
		metrics.put("Classes", ontology.getClassesInSignature().size());
		metrics.put("Object Properties", ontology.getObjectPropertiesInSignature().size());
		metrics.put("Data Properties", ontology.getDataPropertiesInSignature().size());
		metrics.put("Individuals", ontology.getIndividualsInSignature().size());
		
		metrics.put("Axioms", ontology.getAxiomCount());
		metrics.put("Logical Axioms", ontology.getLogicalAxiomCount());
		metrics.put("TBox Axioms", ontology.getTBoxAxioms(false).size());
		metrics.put("RBox Axioms", ontology.getRBoxAxioms(false).size());
		metrics.put("ABox Axioms", ontology.getABoxAxioms(false).size());
		
		for(Entry<String, Object> e: metrics.entrySet()){
			System.out.println(String.format("%s: %s",  e.getKey(), e.getValue()));
		}
		
	}

}
