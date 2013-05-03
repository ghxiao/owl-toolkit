import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;


public class OWLMetricsTest {

	@Test
	public void test() throws OWLOntologyCreationException, IOException {
		OWLMetrics.main("test/univ-bench.owl");
		OWLMetrics.main("test/University0_0.owl");
	}

}
