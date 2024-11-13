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


@WebServlet(urlPatterns = {"/add"})
public class add extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");

        String bookid = request.getParameter("bookid");
        String bookname = request.getParameter("bookname");
        String authorname = request.getParameter("authorname");
        String catgry = request.getParameter("catgry");

        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String pwd = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            Connection con = DriverManager.getConnection(url, user, pwd);

            PreparedStatement ps = con.prepareStatement("INSERT INTO stockk VALUES (?,?,?,?)");
            ps.setString(1, bookid);
            ps.setString(2, bookname);
            ps.setString(3, authorname);
            ps.setString(4, catgry);
            
            int count = ps.executeUpdate();

            if (count > 0) {
                out.print("<h3>Book added Successfully</h3>");
                RequestDispatcher rd = request.getRequestDispatcher("./add.jsp");
                rd.include(request, response);
            } else {
                out.print("<h3>Error: Book could not be added.</h3>");
                RequestDispatcher rd = request.getRequestDispatcher("./error.jsp");
                rd.include(request, response);
            }
        } catch(Exception e) {
            e.printStackTrace();
            out.print("<h3>Database Connection Error</h3>");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);  
    }

    @Override
    public String getServletInfo() {
        return "Servlet that adds products to the database";
    }
}