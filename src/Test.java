import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.reasoner.Reasoner;
import org.mindswap.pellet.jena.PelletReasonerFactory;

/**
 * Created by Le Pham Minh Duc on 12/11/2016.
 */
public class Test {

    public static String url = "http://users.jyu.fi/~miduleph/TIES452/HTML/index.xhtml";
    public static String rules = "@prefix : <http://users.jyu.fi/~miduleph/TIES452/Individuals.owl#> .\n" +
            "@prefix onto: <http://users.jyu.fi/~miduleph/TIES452/Final_Assignment.owl#> .\n" +
            "\n" +
            "{\n" +
            "\t?children onto:hasParent ?parent .\n" +
            "\t?parent onto:hasNationality ?country .\t\n" +
            "}\n" +
            "=>\n" +
            "{\n" +
            "\t?children onto:hasNationality ?country\n" +
            "}.\n";


    public static void main(String [] args) {


        System.out.println(MyReasoner.getResult(url, rules));

//        Model model = RDFAGetter.getModelFromXHTML(sourceURL, baseNameSpace);
//
//        // PelletReasonerFactoryis found in the Pellet API
//        Reasoner reasoner= PelletReasonerFactory.theInstance().create();
//
//        // Obtain standard OWL-DL spec and attach the Pellet reasoner
//        OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
//        ontModelSpec.setReasoner(reasoner);
//        // Create ontology model with reasoner support
//        OntModel ontModel= ModelFactory.createOntologyModel(ontModelSpec, model);
//
//        Dataset dataset = DatasetFactory.create(ontModel);
//        // Parse query string and create Query object
//        Query q = QueryFactory.create(queryString);
//        // Execute query and obtain result set
//        QueryExecution qexec = QueryExecutionFactory.create(q, dataset);
//        ResultSet resultSet = qexec.execSelect();
//        while(resultSet.hasNext()) {
//            QuerySolution row = (QuerySolution)resultSet.next();
//            RDFNode nextMarried = row.get("married");
//            RDFNode nextSpouse = row.get("spouse");
//            System.out.print(nextMarried.toString()+" is married to "+nextSpouse.toString());
//        }

    }
}
