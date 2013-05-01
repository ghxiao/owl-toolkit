import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

public class OWLMergerTest {

	@Test
	public void test() throws OWLOntologyCreationException,
			OWLOntologyStorageException, FileNotFoundException {
		OWLMerger
				.main("/Users/xiao/Downloads/ontology/lubmGenerator/lubm1/University0_0.owl",
						"/Users/xiao/Downloads/ontology/lubmGenerator/lubm1/University0_1.owl");
	}

}
