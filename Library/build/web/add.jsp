<%-- 
    Document   : create
    Created on : 31 Oct, 2024, 4:31:21 PM
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
        <form action="add" method="POST">
           id: <input type="number" name="bookid"><br>
           Book-Name:<input tpye="text" name="bookname"><br>
            Author-Name:<input type="text" name="authorname"><br>
            Book-category:<input type="text" name="catgry"><br>
            <input type="submit" value="submit">
            
        </form>
    </body>
</html>