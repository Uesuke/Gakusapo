<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.SignupUser" %>
<%
    //セッションスコープのユーザー情報を獲得
    SignupUser signupUser = (SignupUser)session.getAttribute("signupUser");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sign-up|GAKUSAPO</title>
		<script src="https://cdn.tailwindcss.com"></script>
	</head>
	<body class="bg-gray-100">
		<jsp:include page="../../header.jsp"/>
		<div class="container mx-auto mt-5">
			<div class="flex justify-center">
				<div class="w-full max-w-lg">
					<h1 class="text-3xl text-center mb-8">ユーザー登録確認画面</h1>
					<div class="bg-white p-8 rounded shadow-lg">
						<div class="p-4 mb-4">
							<p class="mb-2">以下の登録内容に誤りがないか確認してください</p>
							<p class="mb-2">ユーザーID：
							<%=signupUser.getAccountId() %></p>
							<p class="mb-2">名前：
							<%=signupUser.getName() %></p>
							<p class="mb-2">パスワード：
							<%=signupUser.getPass() %></p>
							<p class="mb-2">メールアドレス：
							<%=signupUser.getMail() %></p>
							<p class="mb-2">ユーザータイプ：
							<%if(signupUser.getUserTypeId() == 1){ %>
								個人・学生モード
							<% } else if(signupUser.getUserTypeId() == 2) { %>
								教師モード
							<% } else { %>
								<span class="text-danger">ユーザータイプの取得に失敗しました</span>
							<% } %>
							</p>
							<div class="flex mt-4">
								<form action="CompleteSignupServlet" method="post">
									<button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">確認して登録</button>
								</form>
								<form action="SignupServlet" method="get" class="ml-4">
									<button class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded">登録内容を修正</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
