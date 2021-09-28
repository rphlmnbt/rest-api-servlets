import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import org.json.*;

@WebServlet("/sayhello")
public class HelloServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        //create Json Object
        JSONObject json = new JSONObject();

        // put some value pairs into the JSON object .
        json.put("title", "Hello, World");
        json.put("body", "Hello, world!");

        // Echo client's request information
        json.put("requestURI", request.getRequestURI());
        json.put("protocol", request.getProtocol());
        json.put("pathInfo", request.getPathInfo());
        json.put("remoteAddress", request.getRemoteAddr());

        // Generate a random number upon each request
        json.put("randomNumber", Math.random());

        // finally output the json string
        out.print(json.toString());

        out.close();  // Always close the output writer
    }
}