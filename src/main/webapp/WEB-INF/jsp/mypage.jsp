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
		<title>MyPage|GAKUSAPO</title>
		<script src="https://cdn.tailwindcss.com"></script>
    </head>

	<body class="bg-gray-100">
		<jsp:include page="../../header.jsp"/>
		<div class="container mx-auto mt-5">
			<div class="flex justify-center">
				<div class="w-full max-w-lg">
					<h1 class="text-3xl text-center mb-8">ホーム画面</h1>
					<div class="bg-white p-8 rounded shadow-lg">
						<h2>Profile</h2>
						<p>お名前：<%= user.getName() %>さん</p>
						<p>アカウントID:<%= user.getAccountId() %></p>
						<h2>Link</h2>
						<ul>
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
					    <h2>Records</h2>
						<ul>
							<li>
								「教材名」<br>
								取り組み時間：2h30min
								取り組み範囲：200～213p
					        </li>
					    </ul>
					</div>
	            </div>
	        </div>
	    </div>
	</body>
</html>