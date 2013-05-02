import static org.junit.Assert.*;

import org.junit.Test;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;


public class OWLMetricsTest {

	@Test
	public void test() throws OWLOntologyCreationException {
		OWLMetrics.main("test/univ-bench.owl");
	}

}
