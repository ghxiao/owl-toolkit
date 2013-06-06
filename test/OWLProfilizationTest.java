import org.junit.Test;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;


public class OWLProfilizationTest {

	@Test
	public void test() throws OWLOntologyCreationException, OWLOntologyStorageException {
		OWLProfilization.main("-rl", "test/LUBM-ex-20.owl");
	}

}
