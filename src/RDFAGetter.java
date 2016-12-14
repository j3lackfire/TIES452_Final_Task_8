import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import org.apache.commons.io.FilenameUtils;
import org.semarglproject.jena.core.sink.JenaSink;
import org.semarglproject.rdf.rdfa.RdfaParser;
import org.semarglproject.source.StreamProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import java.net.*;

/**
 * Created by Le Pham Minh Duc on 12/13/2016.
 */
//From the FDFA, return the jena model.
public class RDFAGetter {
    public static Model getModelFromXHTML(String XHTMLFile, String baseRDF) {
        try {
            URL url = new URL(XHTMLFile);
            File rDFaFile = new File(FilenameUtils.getName(url.getPath()));
            FileUtils.copyURLToFile(url, rDFaFile);
            Model model = ModelFactory.createDefaultModel();
            StreamProcessor streamProcessor = new StreamProcessor(RdfaParser.connect(JenaSink.connect(model)));
            List<File> files = listFiles(rDFaFile);
            for (File file : files) {
                streamProcessor.process(file, baseRDF);
            }
            return model;
        }
        catch ( Exception e) {
            return null;
        }
    }

    //I get this file from the library at
    //https://github.com/semarglproject/semargl/blob/master/examples/src/main/java/org/semarglproject/example/BenchmarkRdfa.java
    //but it gives me some weird error that cause other function to not working, so I have to modify it to this.
    private static List<File> listFiles(File dir) {
        ArrayList<File> result = new ArrayList<File>();
        if (dir.exists()) {
            if (dir.getName().endsWith(".xhtml") || dir.getName().endsWith(".html")) {
                result.add(dir);
            }
        }
        return result;
    }

}
