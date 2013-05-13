import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.profiles.OWL2DLProfile;
import org.semanticweb.owlapi.profiles.OWL2ELProfile;
import org.semanticweb.owlapi.profiles.OWL2QLProfile;
import org.semanticweb.owlapi.profiles.OWL2RLProfile;
import org.semanticweb.owlapi.profiles.OWLProfile;
import org.semanticweb.owlapi.profiles.OWLProfileReport;

public class OWLMetrics {

	/**
	 * @param args
	 * @throws OWLOntologyCreationException
	 */
	public static void main(String... args) throws OWLOntologyCreationException {
		OWLOntology ontology = OWLManager.createOWLOntologyManager()
				.loadOntologyFromOntologyDocument(new File(args[0]));
		Map<String, Object> metrics = new LinkedHashMap<>();

		metrics.put("Ontology", ontology.getOntologyID().getOntologyIRI());

		metrics.put("Classes", ontology.getClassesInSignature().size());
		metrics.put("Object Properties", ontology
				.getObjectPropertiesInSignature().size());
		metrics.put("Data Properties", ontology.getDataPropertiesInSignature()
				.size());
		metrics.put("Individuals", ontology.getIndividualsInSignature().size());

		metrics.put("Axioms", ontology.getAxiomCount());
		metrics.put("Logical Axioms", ontology.getLogicalAxiomCount());
		metrics.put("TBox Axioms", ontology.getTBoxAxioms(false).size());
		metrics.put("RBox Axioms", ontology.getRBoxAxioms(false).size());
		metrics.put("ABox Axioms", ontology.getABoxAxioms(false).size());
		metrics.put("Concept Assertions",
				ontology.getAxioms(AxiomType.CLASS_ASSERTION).size());
		metrics.put("Object Property Assertions",
				ontology.getAxioms(AxiomType.OBJECT_PROPERTY_ASSERTION).size());
		metrics.put("Data Property Assertions",
				ontology.getAxioms(AxiomType.DATA_PROPERTY_ASSERTION).size());
		metrics.put("Annotation Assertions",
				ontology.getAxioms(AxiomType.ANNOTATION_ASSERTION).size());

		List<OWLProfile> profiles = new ArrayList<>();

		profiles.add(new OWL2DLProfile());
		profiles.add(new OWL2RLProfile());
		profiles.add(new OWL2ELProfile());
		profiles.add(new OWL2QLProfile());

		for (OWLProfile profile : profiles) {
			OWLProfileReport report = profile.checkOntology(ontology);
			metrics.put(profile.getName(), report.isInProfile());
		}

		for (Entry<String, Object> e : metrics.entrySet()) {
			System.out.println(String.format("%s: %s", // 
					e.getKey(), e.getValue()));
		}
	}

}
