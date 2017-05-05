import org.ghxiao.owltoolkit.OWLIndividuals;
import org.ghxiao.owltoolkit.experimental.FOLConverter;
import org.junit.Test;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import java.io.IOException;

/**
 * Created by simon on 29.04.17.
 */
public class FOLConverterTest {

    @Test
    public void test() throws OWLOntologyCreationException, IOException {
        FOLConverter.main();
    }
}
