<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
//セッションスコープに保存されたユーザー情報を取得
User user = (User)session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>MyPage|Study-with-You</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
		<div class="main">
		    <h1>ホーム画面</h1>
		    <h2>Profile</h2>
		    <p>お名前：<%= user.getName() %>さん</p>
			<p>アカウントID:<%= user.getAccountId() %></p>
			<h2>Link</h2>
			<ul>
				<li><a href="RegisterMaterialServlet">教材登録</a></li>
				<li><a href="MaterialLibraryServlet">教材一覧</a></li>
				<li><a href="RecordProgressServlet">進捗記録</a></li>
				<li><a href="SetGoalServlet">目標登録</a></li>
			</ul>
		    <h2>Records</h2>
		    <ul>
		        <li>
		            「教材名」<br>
		            取り組み時間：2h30min
		            取り組み範囲：200～213p
		        </li>
		    </ul>
		</div>
	</body>
</html>