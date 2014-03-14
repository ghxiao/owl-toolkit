import static org.junit.Assert.*;

import org.junit.Test;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;


public class OWLImportsMaterializationTest {

	@Test
	public void test() throws OWLOntologyCreationException, OWLOntologyStorageException {
		OWLImportsMaterialization.main("test/University0_0.owl");
	}

}
