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
       	<script src="https://cdn.tailwindcss.com"></script>
    </head>

	<body class="bg-gray-100">
		<nav id="header" class="w-full bg-gray-200 border-y-2 border-gray-300">
			<div class="flex justify-between max-w-5xl mx-auto">
				<div class="">
					<a href="IndexServlet"><img src="./images/logo_gakusapo_header.png" class="w-36"></a>
				</div>
				<div class="">
					<% if(user != null) { %>
					<ul class="flex pt-4">
						<li>
							<a href="RegisterMaterialServlet" class="ml-2 bg-gradient-to-br from-green-300 to-green-800 hover:bg-gradient-to-tl text-white rounded px-4 py-2">
								教材登録
							</a>
						</li>
						<li>
							<a href="MaterialLibraryServlet" class="ml-2 bg-gradient-to-br from-green-300 to-green-800 hover:bg-gradient-to-tl text-white rounded px-4 py-2">
								教材一覧
							</a>
						</li>
						<li>
							<a href="RecordProgressServlet" class="ml-2 bg-gradient-to-br from-green-300 to-green-800 hover:bg-gradient-to-tl text-white rounded px-4 py-2">
								進捗記録
							</a>
						</li>
						<li>
							<a href="SetGoalServlet" class="ml-2 bg-gradient-to-br from-green-300 to-green-800 hover:bg-gradient-to-tl text-white rounded px-4 py-2">
								目標登録
							</a>
						</li>
					</ul>
					<% } %>
				</div>
			</div>
	    </nav>
	</body>
</html>