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

<form action="message.jsp" method="POST">
        <button type="submit">WRITE</button>
</form>
<table border="1">

<%
MessageRepository storage = new MessageRepository();
List list = storage.selectAllMessages();
int i = 0;%>
<tr>
            <td>user</td>
            <td>text</td>
</tr>

<% while(i < list.size()){
    if(i%2==0){ %>
        <% }%>
           <tr>
            <td><%= list.get(i) %></td>
            <% i++;  %>
            <td><a href="singleMess.jsp"><%= list.get(i) %></a></td>
          </tr>
<% i++; } %>
</table>



</body>
</html>

