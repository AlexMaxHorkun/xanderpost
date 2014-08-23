<%@ page import="xanderpost.entity.Post" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="header.jsp"%>
<h1>Welcome to XanderPost</h1>
<h3>Posts list:</h3>
<ul>
    <% for(Post p: (List<Post>)request.getAttribute("posts")){ %>
    <li><%= p.getTitle() %> by <%= p.getAuthor().getEmail() %></li>
    <% } %>
</ul>
<%@include file="footer.jsp"%>
