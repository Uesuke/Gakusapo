<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="
	model.User,
	model.Material,
	model.Goal,
	model.GoalDetail,
	java.util.List,
	java.util.Map" %>
<%
//セッションスコープに保存されたユーザー情報を取得
User user = (User)session.getAttribute("user");
//リクエストスコープに保存された目標情報を取得
Goal goal = (Goal)request.getAttribute("goal");
List<GoalDetail> goalDetails = (List<GoalDetail>)request.getAttribute("goalDetails");
Map<GoalDetail, Material> map = (Map<GoalDetail, Material>)request.getAttribute("mapOfGoalDetailAndMaterial");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Goal_Setting|GAKUSAPO</title>
		<script src="https://cdn.tailwindcss.com"></script>
		<script deffer src="./js/goal_set.js"></script>
	</head>
	<body class="bg-gray-100">
		<jsp:include page="../../header.jsp"/>
		<div class="container mx-auto mt-5">
			<div class="flex justify-center">
				<div class="w-full max-w-5xl">
					<div class="bg-white p-8 rounded shadow-lg">
						<div id="content" class="w-4/5 m-auto">
							<h1 id="goalName" class="text-2xl text-center mb-4"><%=goal.getGoalName() %></h1>
							<div class="flex justify-center">
								<div class="w-1/3 text-center bg-blue-100 rounded p-4 mr-1">
									<p>取り組み期間</p>
									<p><%= goal.getDateEndToString() %>まで</p>
								</div>
								<div class="w-1/3 text-center bg-orange-100 rounded p-4 ml-1">
									<p>目標の日まで</p>
									<p>あと<%= goal.getRemainingDays()%>日！</p>
								</div>
							</div>
							<ul>
								<% for(GoalDetail goalDetail: goalDetails){ %>
									<li class="bg-green-100 rounded p-2 pl-6 mt-2">
										<p><%= map.get(goalDetail).getMaterialName() %></p>
										<p><%= goalDetail.getStartFrom() %>～<%= goalDetail.getEndTo() %>ページ</p>
									</li>
								<% } %>
							</ul>
							<p class="text-xl text-center mt-4 mb-2">目標登録完了！</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>