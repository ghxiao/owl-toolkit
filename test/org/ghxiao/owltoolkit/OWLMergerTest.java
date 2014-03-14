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
		OWLMerger.main("test/University0_0.owl", "test/University0_1.owl",
				"test/univ-bench.owl");
	}

}
