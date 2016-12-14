import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.rulesys.GenericRuleReasoner;
import com.hp.hpl.jena.reasoner.rulesys.Rule;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * Created by Le Pham Minh Duc on 12/14/2016.
 */
public class MyReasoner {

    private static String baseNameSpace = "http://users.jyu.fi/~miduleph/TIES452/Individuals.owl#";
    private static String ontoNameSpace = "http://users.jyu.fi/~miduleph/TIES452/Final_Assignment.owl#";
    private static String xmlSchemaNameSpace = "http://www.w3.org/2001/XMLSchema#";
    private static String rdfNameSpace = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    public static String getResult(String rdfaSource, String rules) {
        //generate the model
        Model myModel = RDFAGetter.getModelFromXHTML(rdfaSource, baseNameSpace);
        File myFile = new File("rules.n3");

        try {
            myFile.createNewFile();
            FileWriter fileWriter = new FileWriter(myFile);
            fileWriter.write(formatRuleN3(rules));
            fileWriter.flush();
            fileWriter.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }

        List<Rule> myRules = Rule.rulesFromURL("rules.n3");

        Reasoner reasoner = new GenericRuleReasoner(myRules);
        reasoner.setDerivationLogging(true);

        InfModel inf = ModelFactory.createInfModel(reasoner,myModel);
        StmtIterator it = inf.getDeductionsModel().listStatements();
//        StmtIterator it = inf.listStatements();
        String result = "";
        while ( it.hasNext() )
        {
            Statement stmt = it.nextStatement();
            Resource subject = stmt.getSubject();
            Property predicate = stmt.getPredicate();
            RDFNode object = stmt.getObject();
            String finalString = subject.toString() + " " + predicate.toString() + " " + object.toString();
            result += formatStatement(finalString) + "<br/>\n";
        }
        return result;
    }

    //to add name space
    private static String formatStatement(String statement) {
        String baseString = ":";
        String ontoString = "onto:";
        String xml = "xml:";
        String rdf = "rdf:";
        String returnString = "";
        returnString = statement;
        returnString = returnString.replaceAll(baseNameSpace, baseString);
        returnString = returnString.replaceAll(ontoNameSpace, ontoString);
        returnString = returnString.replaceAll(xmlSchemaNameSpace, xml);
        returnString = returnString.replaceAll(rdfNameSpace, rdf);

        return  returnString;
    }

    //to format the n3 rule because n3 rule and rule in jena are different
    private static String formatRuleN3(String n3Rule) {
        String result = "";
        String prefix = "";
        int firstStatementIndex = 0;
        String firstStatement;
        String secondStatement;
        char[] chars = n3Rule.toCharArray();
        boolean isPrefix = false;
        for (int i = 0; i < chars.length; i ++) {
            if (chars[i] == '@') {
                isPrefix = true;
                prefix += chars[i];
                continue;
            }
            if (chars[i] == '<') {
                continue;
            }
            if (chars[i] == '.') {
                if (isPrefix) {
                    prefix += chars[i];
                }
                continue;
            }
            if (chars[i] == '>') {
                isPrefix = false;
                continue;
            }
            if (chars[i] == '{') {
                firstStatementIndex = i;
                break;
            }
            prefix += chars[i];
        }

        String statement = n3Rule.substring(firstStatementIndex);
        String[] part = statement.split("=>");
        firstStatement = part[0];
        String[] smallStatement = firstStatement.split("\\.");
        firstStatement = "[(";
        if (smallStatement.length > 1) {
            for (int i = 0; i < smallStatement.length - 2; i ++) {
                firstStatement += smallStatement[i] + " )( ";
            }
            firstStatement += smallStatement[smallStatement.length - 2];
        } else {
            firstStatement += smallStatement[0];
        }
        firstStatement += ") -> ";
        secondStatement= part[1];
        smallStatement = secondStatement.split("\\.");
        secondStatement = "(" + smallStatement[0];
        if (smallStatement.length > 1) {
            for (int i = 1; i < smallStatement.length - 1; i ++) {
                secondStatement += smallStatement[i] + " )( ";
            }
        }
        secondStatement += ") ]";

        result = prefix + firstStatement + secondStatement;
        result = result.replaceAll("\\{", "");
        result = result.replaceAll("\\}", "");
        return result;
    }
}
