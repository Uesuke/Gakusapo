<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User, model.Material,java.util.List" %>
<%
//セッションスコープに保存されたユーザー情報を取得
User user = (User)session.getAttribute("user");
//セッションスコープに保存されたMaterialリストを取得
List<Material> materialList = (List<Material>)session.getAttribute("materialList");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Library_Material|Study-with-You</title>
	<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
	 <div class="container mx-auto mt-5">
        <ul class="flex justify-center">
			<% for(Material material : materialList){ %>
				<li>
					<h2><%= material.getMaterialName() %></h2>
					<p><%= material.getPageStart() %>～<%= material.getPageEnd() %>ページ</p>
					<p>総ページ数：<%= material.getTotalPages() %></p>
					<p><%= material.getSectionStart() %>～<%= material.getSectionEnd() %>セクション</p>
					<p>総セクション数：<%= material.getTotalSections() %></p>
				</li>
			<% } %>
        </ul>
    </div>
</body>
</html>