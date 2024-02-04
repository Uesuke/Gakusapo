<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User, model.Material" %>
<%
//セッションスコープに保存されたユーザー情報を取得
User user = (User)session.getAttribute("user");
//セッションスコープに保存されたmaterialを取得
Material lastMaterial = (Material)session.getAttribute("material");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Material_Registeration|Study-with-You</title>
	<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
	 <div class="container mx-auto mt-5">
        <div class="flex justify-center">
			<h2><%= lastMaterial.getMaterialName() %></h2>
			<p><%= lastMaterial.getPageStart() %>～<%= lastMaterial.getPageEnd() %>ページ</p>
			<p>総ページ数：<%= lastMaterial.getTotalPages() %></p>
			<p><%= lastMaterial.getSectionStart() %>～<%= lastMaterial.getSectionEnd() %>セクション</p>
			<p>総セクション数：<%= lastMaterial.getTotalSections() %></p>
        </div>
    </div>
    <p>教材の登録が完了しました</p>
    <ul>
		<li><a href="RegisterMaterialServlet">教材登録</a></li>
		<li><a href="MaterialLibraryServlet">教材一覧</a></li>
		<li><a href="RecordProgressServlet">進捗記録</a></li>
	</ul>
    
</body>
</html>