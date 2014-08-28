<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="xanderpost.entity.readonly.PostInfo" %>
<%@ include file="header.jsp"%>
<h1>Welcome to XanderPost</h1>
<h3>Posts list:</h3>
<ul>
    <% for(PostInfo p: (List<PostInfo>)request.getAttribute("posts")){ %>
    <li>
        <%= p.getTitle() %> by <%= p.getAuthor().getEmail() %> created at <%= p.getCreated() %> having <%= p.getViewsCount() %> views
        <br>
        rated by <%= p.getRatingsCount() %> users with average rating <%= (p.getAvgRating()==null)? 0 : p.getAvgRating() %>
    </li>
    <% } %>
</ul>
<%@ include file="footer.jsp"%>
