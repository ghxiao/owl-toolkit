package org.ghxiao.owltoolkit;
import java.io.IOException;

import org.ghxiao.owltoolkit.OWLClasses;
import org.junit.Test;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;


public class OWLClassesTest {

	@Test
	public void test() throws OWLOntologyCreationException, IOException {
		OWLClasses.main("src/test/resources/University0_0.owl");
		
	}

}
