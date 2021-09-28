import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import org.json.*;

@WebServlet("/query")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class QueryServlet extends HttpServlet {
    // The doGet() runs once per HTTP GET request to this servlet.
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONArray jsonArray = new JSONArray();
        // Set the MIME type for the response message
        response.setContentType("application/json");
        // Get a output writer to write the response message into the network socket
        PrintWriter out = response.getWriter();
        // Print an HTML page as the output of the query
        try (
                // Step 1: Allocate a database 'Connection' object
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                        "root", "password");   // For MySQL
                // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"
                // Step 2: Allocate a 'Statement' object in the Connection
                Statement stmt = conn.createStatement();
        ) {
            // Step 3: Execute a SQL SELECT query
            String sqlStr = "select * from books where author = "
                    + "'" + request.getParameter("author") + "'"   // Single-quote SQL string
                    + " and qty > 0 order by price desc";

            ResultSet rset = stmt.executeQuery(sqlStr);  // Send the query to the server
            // Step 4: Process the query result set
            int count = 0;
            while(rset.next()) {
                // Print a paragraph <p>...</p> for each record
                JSONObject jsonRes = new JSONObject();

                jsonRes.put("author", rset.getString("author"));
                jsonRes.put("title", rset.getString("title"));
                jsonRes.put("price", rset.getDouble("price"));
                jsonRes.put("remoteAddress", request.getRemoteAddr());
                jsonArray.put(jsonRes);
                count++;

            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)

        out.print(jsonArray.toString());
        out.close();
    }
}