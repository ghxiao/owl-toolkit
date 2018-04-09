package org.ghxiao.owltoolkit.experimental;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.model.parameters.Imports;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.semanticweb.owlapi.model.parameters.Imports.INCLUDED;


/**
 * Created by simon on 29.04.17.
 */
public class FOLConverter {

    public static void main() throws OWLOntologyCreationException {

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager
                .loadOntologyFromOntologyDocument(new File("src/test/resources/University0_0.owl"));
        Stream<OWLAxiom> axioms = ontology.tboxAxioms(INCLUDED);
        //axioms.forEach(ax -> System.out.println(ax.getAxiomType()));
        axioms.forEach(ax -> ax.accept(new FOLAxiomConvertVisitor()));


    }
}

class FOLAxiomConvertVisitor implements OWLAxiomVisitor {

    /**
     * visit OWLSubClassOfAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLSubClassOfAxiom axiom) {
        OWLClassExpression subclass = axiom.getSubClass();
        OWLClassExpression superclass = axiom.getSuperClass();
        String subclassString = subclass.accept(new FOLExpressionConvertVisitorX());
        String superclassString = superclass.accept(new FOLExpressionConvertVisitorX());
        System.out.println("∀x." + subclassString + " ⇒ " + superclassString);
    }

    /**
     * visit OWLNegativeObjectPropertyAssertionAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLNegativeObjectPropertyAssertionAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLAsymmetricObjectPropertyAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLAsymmetricObjectPropertyAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLReflexiveObjectPropertyAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLReflexiveObjectPropertyAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLDisjointClassesAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLDisjointClassesAxiom axiom) {
        List<OWLClassExpression> exprs = axiom.getClassExpressionsAsList();
        for(int i = 1 ; i < exprs.size() ; i=i++) {
            String ithElem = exprs.get(i).accept(new FOLExpressionConvertVisitorX());
            for (int j = 1; j < exprs.size(); j++) {
                if (j != i) {
                    String jthElem = exprs.get(j).accept(new FOLExpressionConvertVisitorX());
                    System.out.println("∀x. ¬" + ithElem + " ⇒ " + jthElem);
                }
            }
        }
    }

    /**
     * visit OWLDataPropertyDomainAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLDataPropertyDomainAxiom axiom) {
        String property = (prettyfyName(axiom.getProperty().toString()));
        String domain = (axiom.getDomain().accept(new FOLExpressionConvertVisitorX()));
        System.out.println("∀x.∃y." + property + "(x,y) ⇒ " + domain);
    }

    /**
     * visit OWLObjectPropertyDomainAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLObjectPropertyDomainAxiom axiom) {
        String property = (prettyfyName(axiom.getProperty().toString()));
        String domain = (axiom.getDomain().accept(new FOLExpressionConvertVisitorX()));
        System.out.println("∀x.∃y." + property + "(x,y) ⇒ " + domain);
    }

    /**
     * visit OWLEquivalentObjectPropertiesAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLEquivalentObjectPropertiesAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLNegativeDataPropertyAssertionAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLNegativeDataPropertyAssertionAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLDifferentIndividualsAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLDifferentIndividualsAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLDisjointDataPropertiesAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLDisjointDataPropertiesAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLDisjointObjectPropertiesAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLDisjointObjectPropertiesAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLObjectPropertyRangeAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLObjectPropertyRangeAxiom axiom) {
        String property = (prettyfyName(axiom.getProperty().toString()));
        String range = (axiom.getRange().accept(new FOLExpressionConvertVisitorX()));
        System.out.println("∀x.∃y." + property + "(y,x) ⇒ " + range);
    }

    /**
     * visit OWLObjectPropertyAssertionAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLObjectPropertyAssertionAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLFunctionalObjectPropertyAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLFunctionalObjectPropertyAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLSubObjectPropertyOfAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLSubObjectPropertyOfAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLDisjointUnionAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLDisjointUnionAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLSymmetricObjectPropertyAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLSymmetricObjectPropertyAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLDataPropertyRangeAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLDataPropertyRangeAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLFunctionalDataPropertyAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLFunctionalDataPropertyAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLEquivalentDataPropertiesAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLEquivalentDataPropertiesAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLClassAssertionAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLClassAssertionAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLEquivalentClassesAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLEquivalentClassesAxiom axiom) {
        List<OWLClassExpression> exprs = axiom.getClassExpressionsAsList();
        String firstElement = exprs.get(0).accept(new FOLExpressionConvertVisitorX());
        for(int i = 1 ; i < exprs.size(); i++)
        {
            String ithElem = exprs.get(i).accept(new FOLExpressionConvertVisitorX());
            System.out.println("∀x." + firstElement + " ⇔ " + ithElem);
        }
    }

    /**
     * visit OWLDataPropertyAssertionAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLDataPropertyAssertionAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLTransitiveObjectPropertyAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLTransitiveObjectPropertyAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLIrreflexiveObjectPropertyAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLIrreflexiveObjectPropertyAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLSubDataPropertyOfAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLSubDataPropertyOfAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLInverseFunctionalObjectPropertyAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLInverseFunctionalObjectPropertyAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLSameIndividualAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLSameIndividualAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLSubPropertyChainOfAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLSubPropertyChainOfAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLInverseObjectPropertiesAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLInverseObjectPropertiesAxiom axiom) {
        System.out.println("not yet done");
    }

    /**
     * visit OWLHasKeyAxiom type
     *
     * @param axiom axiom to visit
     */
    public void visit(OWLHasKeyAxiom axiom) {
        System.out.println("not yet done");
    }

    private static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    private static String prettyfyName(String str) {
        String[] parts = str.split("#");
        return (removeLastChar(parts[1]));
    }
}

class FOLExpressionConvertVisitorX implements OWLClassExpressionVisitorEx<String> {

    public String visit(OWLClass ce) {
        return (prettyfyName(ce.toString()) + "(x)");
    }

    public String visit(OWLNamedObject ce) {
        return (prettyfyName(ce.toString()) + "(x)");
    }

    public String visit(OWLObjectIntersectionOf ce) {
         Stream<OWLClassExpression> operands = ce.operands();
         Stream<String> opStrings = operands.map(op -> op.accept(new FOLExpressionConvertVisitorX()));
         return ("(" + conjunctionAsString(opStrings) + ")");
    }

     public String visit(OWLObjectUnionOf ce) {
         Stream<OWLClassExpression> operands = ce.operands();
         Stream<String> opStrings = operands.map(op -> op.accept(new FOLExpressionConvertVisitorX()));
         return ("(" + unionAsString(opStrings) + ")");
    }

     public String visit(OWLObjectComplementOf ce) {
        return ("¬" + prettyfyName(ce.getOperand().toString()));
    }

     public String visit(OWLObjectSomeValuesFrom ce) {
        OWLPropertyExpression prop = ce.getProperty();
        OWLClassExpression filler = ce.getFiller();
        String fillerString = filler.accept(new FOLExpressionConvertVisitorY());
        String propString = prettyfyName(prop.toString());
        return ("∃y(" + propString + "(x,y) ∧ " + fillerString + ")");
    }

     public String visit(OWLObjectAllValuesFrom ce) {
        return (ce.toString());
    }

     public String visit(OWLObjectHasValue ce) {
        return (ce.toString());
    }

    private static String removeLastChar(String str) {
        return str.substring(0,str.length()-1);
    }

    private static String prettyfyName(String str) {
        String[] parts = str.split("#");
        return (removeLastChar(parts[1]));
    }

    private static String conjunctionAsString(Stream<String> conjuncts){
        return removeLastChar(removeLastChar(removeLastChar(conjuncts.reduce("", (x,y) -> x + y + " ∧ "))));
    }

    private static String unionAsString(Stream<String> disjuncts){
        return removeLastChar(removeLastChar(removeLastChar(disjuncts.reduce("", (x,y) -> x + y + " ∨ "))));

    }
 
 }

class FOLExpressionConvertVisitorY implements OWLClassExpressionVisitorEx<String> {

    public String visit(OWLClass ce) {
        return (prettyfyName(ce.toString()) + "(y)");
    }

    public String visit(OWLNamedObject ce) {
        return (prettyfyName(ce.toString()) + "(y)");
    }

    public String visit(OWLObjectIntersectionOf ce) {
        Stream<OWLClassExpression> operands = ce.operands();
        Stream<String> opStrings = operands.map(op -> op.accept(new FOLExpressionConvertVisitorY()));
        return ("(" + conjunctionAsString(opStrings) + ")");
    }

    public String visit(OWLObjectUnionOf ce) {
        Stream<OWLClassExpression> operands = ce.operands();
        Stream<String> opStrings = operands.map(op -> op.accept(new FOLExpressionConvertVisitorY()));
        return ("(" + unionAsString(opStrings) + ")");
    }

    public String visit(OWLObjectComplementOf ce) {
        return ("¬" + prettyfyName(ce.getOperand().toString()));
    }

    public String visit(OWLObjectSomeValuesFrom ce) {
        OWLPropertyExpression prop = ce.getProperty();
        OWLClassExpression filler = ce.getFiller();
        String fillerString = filler.accept(new FOLExpressionConvertVisitorX());
        String propString = prettyfyName(prop.toString());
        return ("∃x(" + propString + "(y,x) ∧ " + fillerString + ")");
    }

    public String visit(OWLObjectAllValuesFrom ce) {
        return (ce.toString());
    }

    public String visit(OWLObjectHasValue ce) {
        return (ce.toString());
    }

    private static String removeLastChar(String str) {
        return str.substring(0,str.length()-1);
    }

    private static String prettyfyName(String str) {
        String[] parts = str.split("#");
        return (removeLastChar(parts[1]));
    }

    private static String conjunctionAsString(Stream<String> conjuncts){
        return removeLastChar(removeLastChar(removeLastChar(conjuncts.reduce("", (x,y) -> x + y + " ∧ "))));
    }

    private static String unionAsString(Stream<String> disjuncts){
        return removeLastChar(removeLastChar(removeLastChar(disjuncts.reduce("", (x,y) -> x + y + " ∨ "))));

    }

}