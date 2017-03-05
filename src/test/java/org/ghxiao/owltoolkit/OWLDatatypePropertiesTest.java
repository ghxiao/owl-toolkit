package org.ghxiao.owltoolkit;

import org.junit.Test;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import java.io.IOException;


public class OWLDatatypePropertiesTest {

	@Test
	public void test() throws OWLOntologyCreationException, IOException {
		OWLDataProperties.main("src/test/resources/LUBM-ex-20.owl");
		
	}

}
