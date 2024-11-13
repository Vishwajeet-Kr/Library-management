<%-- 
    Document   : delete
    Created on : 31 Oct, 2024, 4:32:05 PM
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
        <form action="delete" method="DELETE">
            <h3>Enter the Book id to delete from library database.</h3>
            id:<input type="number" name="bookid"><br>
            <input type="submit" value="submit">
        </form>
        
    </body>
</html>