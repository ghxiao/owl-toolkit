package org.ghxiao.owltoolkit;

import org.junit.Test;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

import java.io.IOException;


public class OWLDeclarationsTest {

	@Test
	public void test() throws OWLOntologyCreationException, IOException, OWLOntologyStorageException {
		OWLDeclarations.main("src/test/resources/LUBM-ex-20.owl");
	}

}
