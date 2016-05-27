<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="store.MessageRepository" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border="1">

<%
MessageRepository storage = new MessageRepository();
List list = storage.getMessages();
int i = 0;%>
           <tr>
            <td><%= list.get(i) %></td>
            <% i++;  %>
            <td><%= list.get(i) %></td>
          </tr>
</table>



</body>
</html>

