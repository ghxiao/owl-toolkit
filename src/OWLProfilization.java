import java.io.File;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
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
import org.semanticweb.owlapi.util.OWLAxiomVisitorAdapter;

public class OWLProfilization {
	public static void main(String... args)
			throws OWLOntologyCreationException, OWLOntologyStorageException {
		if (args.length != 2){
			printUsage();
			System.exit(0);
		}
		
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

		ontology = new OWLEquvilanceToSubsumptionsConverter().convert(ontology,
				manager);
		
		OWLProfileReport report = profile.checkOntology(ontology);

		System.err.println(report.toString());

		for (OWLProfileViolation violation : report.getViolations()) {
			OWLAxiom axiom = violation.getAxiom();
			manager.removeAxiom(ontology, axiom);
		}

		manager.saveOntology(ontology, System.out);
	}

	private static void printUsage() {
		System.err.println("Usage: \nowl-profilize {-rl | -el | -ql} file.owl");
		
	}
}

class OWLEquvilanceToSubsumptionsConverter extends OWLAxiomVisitorAdapter {

	OWLOntology ont;
	private OWLOntologyManager manager;

	public OWLOntology convert(OWLOntology ontology, OWLOntologyManager manager) {
		this.ont = ontology;
		this.manager = manager;
		
		for(OWLAxiom axiom : ontology.getAxioms()){
			axiom.accept(this);
		}
		
		return this.ont;
	}

	@Override
	public void visit(OWLEquivalentClassesAxiom axiom) {

		Set<OWLClassExpression> classes = axiom.getClassExpressions();
		for (OWLClassExpression cls1 : classes) {
			for (OWLClassExpression cls2 : classes) {
				if (cls1 != cls2) {
					manager.addAxiom(ont, manager.getOWLDataFactory()
							.getOWLSubClassOfAxiom(cls1, cls2));
				}
			}
		}

		manager.removeAxiom(ont, axiom);
	}

}
