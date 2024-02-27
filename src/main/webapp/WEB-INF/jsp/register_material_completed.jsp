<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User, model.Material" %>
<%
//セッションスコープに保存されたユーザー情報を取得
User user = (User)session.getAttribute("user");
//セッションスコープに保存されたmaterialを取得
Material material = (Material)session.getAttribute("material");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Progress_Record|GAKUSAPO</title>
		<script src="https://cdn.tailwindcss.com"></script>
	</head>
	<body class="bg-gray-100">
		<jsp:include page="../../header.jsp"/>
		<div class="container mx-auto mt-5">
			<div class="flex justify-center">
				<div class="w-full max-w-5xl">
					<div class="bg-white p-8 rounded shadow-lg">
						<div id="content" class="w-4/5 m-auto">
							<div class="w-2/3 text-center border border-gray-400 rounded p-4 mx-auto">
								<p class="text-xl mt-2 mb-4">${ material.materialName }</p>
								<p>ページ数</p>
								<p class="mb-2">${ material.pageStart }～${ material.pageEnd }ページ</p>
								<p>単位数</p>
								<p class="mb-2">${ material.sectionStart }～${ material.sectionEnd }単位</p>
							</div>
							<p class="text-xl text-center mt-4 mb-2">登録完了！</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>