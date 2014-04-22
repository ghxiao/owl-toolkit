package org.ghxiao.owltoolkit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.RDFXMLOntologyFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyAxiom;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

public class OWLPredicateRemover {

	/**
	 * @param args
	 * @throws OWLOntologyCreationException
	 * @throws OWLOntologyStorageException
	 * @throws FileNotFoundException
	 */
	public static void main(String... args)
			throws OWLOntologyCreationException, OWLOntologyStorageException,
			FileNotFoundException {
		if (args.length == 0){
			System.err.println("Usage: owl-pred-remove pred1 ... predn  file.owl");
			System.exit(0);
		}
		
		List<String> preds = new ArrayList<>();
		
		for(int i = 0 ; i < args.length - 1; i++){
			preds.add(args[i]);
		}
		
		// the last one is the ontology
		String ontologyFile =  args[args.length-1];
				
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLOntology ontology = manager
						.loadOntologyFromOntologyDocument(new File(ontologyFile));
		
		OWLDataFactory factory = manager.getOWLDataFactory();
		
		
		for(String pred : preds){
			OWLClass owlClass = factory.getOWLClass(IRI.create(pred));
			Set<OWLClassAxiom> axioms = ontology.getAxioms(owlClass);
			if(axioms.size() > 0){
				System.err.println("remove " + axioms.size() + " axioms with class " + owlClass);
				manager.removeAxioms(ontology, axioms);
			}
			
			
			OWLObjectProperty objProp = factory.getOWLObjectProperty(IRI.create(pred));
			
			Set<OWLObjectPropertyAxiom> axioms2 = ontology.getAxioms(objProp);
			if(axioms2.size() > 0){
				System.err.println("remove " + axioms2.size() + " axioms with object property " + objProp);
				manager.removeAxioms(ontology, axioms2);
			}
			
			OWLDataProperty dataProp = factory.getOWLDataProperty(IRI.create(pred));
			Set<OWLDataPropertyAxiom> axioms3 = ontology.getAxioms(dataProp);
			if (axioms.size() > 0){
				System.err.println("remove " + axioms3.size() + " axioms with object property " + dataProp);
				manager.removeAxioms(ontology, axioms3);
			}
		}
		
		Set<OWLAxiom> aBoxAxioms = ontology.getABoxAxioms(false);
		
		List<OWLAxiom> axiomsToRemove = new ArrayList<>();
		for(OWLAxiom ax : aBoxAxioms){
			if(ax instanceof OWLClassAssertionAxiom){
				String string = ((OWLClassAssertionAxiom)ax).getClassExpression().asOWLClass().getIRI().toString();
				if(preds.contains(string)){
					axiomsToRemove.add(ax);
					System.err.println("remove  axioms with class " + string);

				}
			} else if (ax instanceof OWLObjectPropertyAssertionAxiom){
				String string = ((OWLObjectPropertyAssertionAxiom)ax).getProperty().asOWLObjectProperty().getIRI().toString();
				if(preds.contains(string)){
					axiomsToRemove.add(ax);
					System.err.println("remove  axioms with object property " + string);
				}
			}else if (ax instanceof OWLDataPropertyAssertionAxiom){
				String string = ((OWLDataPropertyAssertionAxiom)ax).getProperty().asOWLDataProperty().getIRI().toString();
				if(preds.contains(string)){
					axiomsToRemove.add(ax);
					System.err.println("remove  axioms with data property " + string);
				}
			}
		}
		
		manager.removeAxioms(ontology, new HashSet<OWLAxiom>( axiomsToRemove));
		
		manager.saveOntology(ontology, new RDFXMLOntologyFormat(), System.out);

	}

}
