<%@ page import="java.util.List" %>
<%@ page import="xanderpost.entity.Post" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <h2>Post list</h2>
    <ul>
        <% List<Post> posts=(ArrayList<Post>)request.getAttribute("posts"); %>
        <% for(Post post: posts){ %>
        <li><%= post.getTitle() %></li>
        <% } %>
    </ul>
</body>
</html>
