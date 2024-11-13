/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *
 * @author bishw
 */

@WebServlet(urlPatterns = {"/delete"})
public class delete extends HttpServlet {

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");

        String bookid = request.getParameter("bookid");

        String url = "jdbc:mysql://localhost:3306/library"; 
        String user = "root";
        String pwd = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            Connection con = DriverManager.getConnection(url, user, pwd);

            PreparedStatement ps = con.prepareStatement("DELETE FROM stockk WHERE bookid = ?");
            ps.setString(1, bookid);

            int count = ps.executeUpdate();

            if (count > 0) {
                out.print("<h3>Book Informanation deleted successfully.</h3>");
                RequestDispatcher rd = request.getRequestDispatcher("./delete.jsp");
                rd.include(request, response);
            } else {
                out.print("<h3>Error: Book ID mismatch. Could not delete.</h3>");
                RequestDispatcher rd = request.getRequestDispatcher("./error.jsp");
                rd.include(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.print("<h3>Database Connection Error</h3>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doDelete(request, response);  
    }

    @Override
    public String getServletInfo() {
        return "Servlet that deletes a product from the database by ID";
    }
}