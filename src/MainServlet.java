import org.apache.jena.rdf.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

/**
 * Created by Le Pham Minh Duc on 12/11/2016.
 */
@WebServlet(name = "MainServlet")
public class MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String urlString = request.getParameter("URL");
        String rule = request.getParameter("rule");

        try (PrintWriter writer = response.getWriter()) {

            writer.println("<!DOCTYPE html><html>");
            writer.println("<head>");
            writer.println("<meta charset=\"UTF-8\" />");
            writer.println("<title>Website title</title>");
            writer.println("</head>");
            writer.println("<body>");

            writer.println("<h1> URL </h1>");
            writer.println("<h2>  "+ Test.url + " </h2>");
            writer.println("<h1> Rules </h1>");
            writer.println("<h2>  "+ Test.rules + " </h2>");
            writer.println("<h2> RESULTS </h2>");
            writer.println("<p>  "+ MyReasoner.getResult(Test.url, Test.rules) + " </p>");

            writer.println("</body>");
            writer.println("</html>");
        }
    }

    protected String formatToHTML(String inputString) {
        String returnString = inputString;
        return "";
    }
}
