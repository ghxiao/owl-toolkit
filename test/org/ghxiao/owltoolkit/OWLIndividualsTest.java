package org.ghxiao.owltoolkit;
import java.io.IOException;

import org.ghxiao.owltoolkit.OWLIndividuals;
import org.junit.Test;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;


public class OWLIndividualsTest {

	@Test
	public void test() throws OWLOntologyCreationException, IOException {
		OWLIndividuals.main("test/University0_0.owl");
		
	}

}
