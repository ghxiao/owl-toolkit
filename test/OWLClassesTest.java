import java.io.IOException;

import org.junit.Test;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;


public class OWLClassesTest {

	@Test
	public void test() throws OWLOntologyCreationException, IOException {
		OWLClasses.main("test/University0_0.owl");
		
	}

}
