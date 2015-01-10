package org.ghxiao.owltoolkit;
import java.io.IOException;

import org.ghxiao.owltoolkit.OWLMetrics;
import org.junit.Test;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;


public class OWLMetricsTest {

	@Test
	public void test() throws OWLOntologyCreationException, IOException {
		OWLMetrics.main("-v", "src/test/resources/univ-bench.owl");
		//OWLMetrics.main("test/University0_0.owl");
		//OWLMetrics.main("-v", "test/LUBM-ex-20.owl");
	}

}
