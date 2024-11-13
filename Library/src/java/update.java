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

/**
 *
 * @author bishw
 */

@WebServlet("/update")
public class update extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String bookid = request.getParameter("bookid");
        String bookname = request.getParameter("bookname");
        String authorname = request.getParameter("authorname");
        String catgry = request.getParameter("category");

        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String pwd = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pwd);

            // Dynamic SQL to update only provided fields
            StringBuilder sql = new StringBuilder("UPDATE stockk SET ");
            boolean isFirst = true;

            if (bookname != null && !bookname.trim().isEmpty()) {
                sql.append("bookname = ?");
                isFirst = false;
            }
            if (authorname != null && !authorname.trim().isEmpty()) {
                if (!isFirst) sql.append(", ");
                sql.append("authorname = ?");
                isFirst = false;
            }
            if (catgry != null && !catgry.trim().isEmpty()) {
                if (!isFirst) sql.append(", ");
                sql.append("category = ?");
            }
            sql.append(" WHERE bookid = ?");

            PreparedStatement ps = con.prepareStatement(sql.toString());

            int parameterIndex = 1;
            if (bookname != null && !bookname.trim().isEmpty()) {
                ps.setString(parameterIndex++, bookname);
            }
            if (authorname != null && !authorname.trim().isEmpty()) {
                ps.setString(parameterIndex++, authorname);
            }
            if (catgry != null && !catgry.trim().isEmpty()) {
                ps.setString(parameterIndex++, catgry);
            }
            ps.setString(parameterIndex, bookid);

            int count = ps.executeUpdate();

            if (count > 0) {
                request.setAttribute("message", "Book updated successfully.");
            } else {
                request.setAttribute("message", "Error: Book with ID " + bookid + " not found.");
            }

            RequestDispatcher rd = request.getRequestDispatcher("update.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Database Connection Error: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("update.jsp");
            rd.forward(request, response);
        }
    }
}