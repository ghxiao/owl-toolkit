import java.io.File;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.profiles.OWL2ELProfile;
import org.semanticweb.owlapi.profiles.OWL2QLProfile;
import org.semanticweb.owlapi.profiles.OWL2RLProfile;
import org.semanticweb.owlapi.profiles.OWLProfile;
import org.semanticweb.owlapi.profiles.OWLProfileReport;
import org.semanticweb.owlapi.profiles.OWLProfileViolation;

public class OWLProfilization {
	public static void main(String... args) throws OWLOntologyCreationException,
			OWLOntologyStorageException {
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLOntology ontology = manager
				.loadOntologyFromOntologyDocument(new File(args[1]));

		OWLProfile profile;
		switch (args[0]) {
		case "-rl":
			profile = new OWL2RLProfile();
			break;
		case "-el":
			profile = new OWL2ELProfile();
			break;
		case "-ql":
			profile = new OWL2QLProfile();
			break;
		default:
			throw new IllegalStateException("Unknow profile: " + args[0]);
		}

		OWLProfileReport report = profile.checkOntology(ontology);

		System.err.println(report.toString());
		
		for (OWLProfileViolation violation : report.getViolations()) {
			OWLAxiom axiom = violation.getAxiom();
			manager.removeAxiom(ontology, axiom);
		}

		manager.saveOntology(ontology, System.out);
	}
}
