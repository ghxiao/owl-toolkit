package org.ghxiao.owltoolkit;
import org.ghxiao.owltoolkit.OWLImportsMaterialization;
import org.junit.Test;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;


public class OWLImportsMaterializationTest {

	@Test
	public void test() throws OWLOntologyCreationException, OWLOntologyStorageException {
		OWLImportsMaterialization.main("src/test/resources/University0_0.owl");
	}

}
