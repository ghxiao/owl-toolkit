package org.ghxiao.owltoolkit;
import java.io.FileNotFoundException;

import org.ghxiao.owltoolkit.OWLMerger;
import org.junit.Test;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

public class OWLMergerTest {

	@Test
	public void test() throws OWLOntologyCreationException,
			OWLOntologyStorageException, FileNotFoundException {
		OWLMerger.main("src/test/resources/University0_0.owl", "src/test/resources/University0_1.owl",
				"src/test/resources/univ-bench.owl");
	}

}
