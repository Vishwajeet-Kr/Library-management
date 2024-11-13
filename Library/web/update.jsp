<%-- 
    Document   : update
    Created on : 3 Nov, 2024, 6:27:46 PM
    Author     : bishw
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="update" method="POST">
            id: <input type="number" name="bookid"><br>
            Book-Name:<input tpye="text" name="bookname"><br>
            Author-Name:<input type="text" name="authorname"><br>
            Book-category:<input type="text" name="category"><br>
            <input type="submit" value="submit">
        </form>
        
        <div>
        <%
            // Display any messages from the servlet (like success or error messages)
            String message = (String) request.getAttribute("message");
            if (message != null) {
                out.println("<p style='color:blue;'>" + message + "</p>");
            }
        %>
    </div>
    </body>
</html>