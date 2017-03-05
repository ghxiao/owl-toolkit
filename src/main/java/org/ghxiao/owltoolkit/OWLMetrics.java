package org.ghxiao.owltoolkit;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.parameters.Imports;
import org.semanticweb.owlapi.profiles.OWL2DLProfile;
import org.semanticweb.owlapi.profiles.OWL2ELProfile;
import org.semanticweb.owlapi.profiles.OWL2QLProfile;
import org.semanticweb.owlapi.profiles.OWL2RLProfile;
import org.semanticweb.owlapi.profiles.OWLProfileReport;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class OWLMetrics {

    public static void main(String... args) throws OWLOntologyCreationException {


        if (args.length == 0) {
            System.err.println("Usage: owl-metrics [-v] file.owl");
            System.exit(0);
        }

        final boolean verbose = args[0].equals("-v") || args[0].equals("-verbose");

        int i = verbose ? 1 : 0;

        OWLOntology ontology = OWLManager.createOWLOntologyManager().loadOntologyFromOntologyDocument(new File(args[i]));
        Map<String, Object> metrics = new LinkedHashMap<>();

        ontology.getOntologyID().getOntologyIRI().ifPresent(iri -> metrics.put("Ontology", iri));

        metrics.put("Classes", ontology.classesInSignature().count());
        metrics.put("Object Properties", ontology.objectPropertiesInSignature().count());
        metrics.put("Data Properties", ontology.dataPropertiesInSignature().count());
        metrics.put("Individuals", ontology.individualsInSignature().count());

        metrics.put("Axioms", ontology.getAxiomCount());
        metrics.put("Logical Axioms", ontology.getLogicalAxiomCount());
        metrics.put("TBox Axioms", ontology.tboxAxioms(Imports.EXCLUDED).count());
        metrics.put("RBox Axioms", ontology.rboxAxioms(Imports.EXCLUDED).count());
        metrics.put("ABox Axioms", ontology.aboxAxioms(Imports.EXCLUDED).count());
        metrics.put("Concept Assertions", ontology.axioms(AxiomType.CLASS_ASSERTION).count());
        metrics.put("Object Property Assertions", ontology.axioms(AxiomType.OBJECT_PROPERTY_ASSERTION).count());
        metrics.put("Data Property Assertions", ontology.axioms(AxiomType.DATA_PROPERTY_ASSERTION).count());
        metrics.put("Annotation Assertions", ontology.axioms(AxiomType.ANNOTATION_ASSERTION).count());
        metrics.put("SWRL Rules", ontology.axioms(AxiomType.SWRL_RULE).count());

        Stream.of(new OWL2DLProfile(), new OWL2RLProfile(), new OWL2ELProfile(), new OWL2QLProfile())
                .forEach(profile -> {
                            OWLProfileReport report = profile.checkOntology(ontology);
                            if (!verbose) {
                                metrics.put(profile.getName(), report.isInProfile());
                            } else {
                                metrics.put(profile.getName(), report.toString());
                            }
                        }
                );

        metrics.forEach((k, v) -> System.out.format("%s: %s\n", k, v));

    }

}
