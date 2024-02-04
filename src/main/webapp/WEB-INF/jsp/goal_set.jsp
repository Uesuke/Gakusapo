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
	<title>Goal_Setting|Study-with-You</title>
	<script src="https://cdn.tailwindcss.com"></script>
	<script deffer src="./js/goal_set.js"></script>
</head>
<body class="bg-gray-100">
	 <div class="container mx-auto mt-5">
        <div class="flex justify-center">
            <div class="w-full max-w-lg">
				<h1 class="text-3xl text-center mb-8">目標登録画面</h1>
				<div class="bg-white p-8 rounded shadow-lg">
					<form action="SetGoalServlet" method="post">
						<div class="mb-6">
							<label for="goalName" class="block mb-2">目標名</label>
							<input type="text" id="goalName" class="form-input border-2 border-gray-500" name="goalName">
						</div>
						<div class="mb-6">
							<p>
								<label for="dateStart" class="block mb-2">開始日</label>
								<input type="date" id="dateStart" class="form-input border-2 border-gray-500" name="startFrom">
							</p>
							<p>
								<label for="dateEnd" class="block mb-2">終了日</label>
								<input type="date" id="dateEnd" class="form-input border-2 border-gray-500" name="endTo">
							</p>
						</div>
						<div class="mb-6">
							<label for="standardTypeId" class="block mb-2">達成条件</label>
							<input type="radio" id="byPage" class="form-input border-2 border-gray-500" name="standardTypeId" value="1">ページで評価
							<input type="radio" id="byTime" class="form-input border-2 border-gray-500" name="standardTypeId" value="2">時間で評価
						</div>
						<ul id="formList">
							<li id="formItem_" class="flex">
								<div class="mb-2">
									<label for="materialId" class="block mb-2">教材・ページ</label>
									<select id="materialId_0" class="form-input border-2 border-gray-500" name="materialId_0">
										<option value="" selected hidden>教材を選択</option>
										<% for(Material material: materialList){ %>
										<option value="<%= material.getMaterialId() %>" label="<%= material.getMaterialName() %>" >
										<% } %>
									</select>
									<input type="number" id="startFrom_0" class="form-input w-16 border-2 border-gray-500" name="startFrom_0">
									～
									<input type="number" id="endTo_0" class="form-input w-16 border-2 border-gray-500" name="endTo_0">
								</div>
							</li>
						</ul>
						<template id="formTemplate">
							<li id="formItem_" class="flex">
								<div class="mb-2">
									<label for="materialId" class="block mb-2">教材・ページ</label>
									<select id="materialId_" class="form-input border-2 border-gray-500" name="materialId_">
										<option value="" selected hidden>教材を選択</option>
										<% for(Material material: materialList){ %>
										<option value="<%= material.getMaterialId() %>" label="<%= material.getMaterialName() %>" >
										<% } %>
									</select>
									<input type="number" id="startFrom_" class="form-input w-16 border-2 border-gray-500" name="startFrom_">
									～
									<input type="number" id="endTo_" class="form-input w-16 border-2 border-gray-500" name="endTo_">
								</div>
							</li>
						</template>
						<input id="num_materials" type="hidden" value="1" name="num_materials">
						<button type="button" id="add-form" onclick="addForm()" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded">教材を追加</button>
						<button type="button" id="deleteBtn" onclick="deleteForm()" style="visibility: hidden" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">教材を削除</button>
						<div>
							<input type="checkbox" class="form-input border-2 border-gray-500 mb-2" name="isShared">
							先生と共有
						</div>
						<button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">登録</button>
					</form>
				</div>
            </div>
        </div>
    </div>
</body>
</html>