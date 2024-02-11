<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User, model.Material,java.util.List" %>
<%
//セッションスコープに保存されたユーザー情報を取得
User user = (User)session.getAttribute("user");
//セッションスコープに保存された教材リストを取得
List<Material> materialList = (List<Material>)session.getAttribute("materialList");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Progress_Record|Study-with-You</title>
	<script src="https://cdn.tailwindcss.com"></script>
	<script deffer src="./js/date.js"></script>
</head>
<body class="bg-gray-100">
	<jsp:include page="../../header.jsp"/>
	<div class="container mx-auto mt-5">
		<div class="flex justify-center">
			<div class="w-full max-w-lg">
				<h1 class="text-3xl text-center mb-8">進捗記録画面</h1>
				<div class="bg-white p-8 rounded shadow-lg">
					<form action="RecordProgressServlet" method="post">
						<div class="mb-6">
							<label for="date" class="block mb-2">日付</label>
							<input type="date" id="dateInput" class="form-input border-2 border-gray-500" name="dateInput" onchange="extractDate(this)">
							<Input type="hidden" id ="date" name="date">
						</div>
						<div class="mb-6">
							<p>
								<label for="time" class="block mb-2">取り組み時間</label>
								<input type="number" id="hour" class="form-input border-2 border-gray-500" value="0" max="24" min="0" name="hour">時間
								<input type="number" id="munite" class="form-input border-2 border-gray-500" value="0" max="59" min="0" name="munite">分
							</p>
						</div>
						<div class="mb-6">
							<label for="materialId" class="block mb-2">教材</label>
							<select id="materialId" class="form-input border-2 border-gray-500" name="materialId">
								<option value="" selected hidden>教材を選択</option>
								<% for(Material material: materialList){ %>
								<option value="<%= material.getMaterialId() %>" label="<%= material.getMaterialName() %>" >
								<% } %>
							</select>
							<p><a href="RegisterMaterialServlet" class="underline">教材を新しく登録</a></p>
						</div>
						<div class="mb-6">
							<label for="pageStart" class="block mb-2">ページ</label>
							<input type="number" id="pageStart" class="form-input border-2 border-gray-500" name="pageStart">
							～
							<input type="number" id="pageEnd" class="form-input border-2 border-gray-500" name="pageEnd">
						</div>
						<div>
							<label for="isShared" class="block mb-2">先生と共有</label>
							<input type="checkbox" class="form-input border-2 border-gray-500" name="isShared" value="1">
						</div>
						<button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">登録</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>