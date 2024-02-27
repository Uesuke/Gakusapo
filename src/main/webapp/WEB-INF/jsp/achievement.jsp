<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="
	model.User,
	model.Material,
	model.Goal,
	model.GoalDetail,
	java.util.List,
	java.util.Map
" %>
<%
//セッションスコープに保存されたユーザー情報を取得
User user = (User)session.getAttribute("user");
//セッションスコープに保存されたGoalを取得
Goal checkGoal = (Goal)session.getAttribute("checkGoal");
//セッションスコープに保存されたGoalDetailsリストを取得
List<GoalDetail> goalDetails = (List<GoalDetail>)session.getAttribute("goalDetails");
//セッションスコープに保存されたマップを取得
Map<GoalDetail, Material> mapOfGoalDetailsAndMaterial = (Map<GoalDetail, Material>)session.getAttribute("mapOfGoalDetailsAndMaterial");
Map<Integer, Integer> mapOfGoalDetailsAndAchievmentRatio = (Map<Integer, Integer>)session.getAttribute("mapOfGoalDetailsAndAchievmentRatio");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Achiecment | GAKUSAPO</title>
		<script src="https://cdn.tailwindcss.com"></script>
		<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	</head>
	<body class="bg-gray-100">
		<jsp:include page="../../header.jsp"/>
		<div class="container w-[1024px] mx-auto mt-5">
			<div class="flex justify-center">
				<div class="w-full max-w-5xl">
					<div class="bg-white p-8 rounded shadow-lg">
						<div id="content" class="m-auto">
							<h1 class="text-xl text-center">${ checkGoal.goalName }</h1>
							<div class="flex justify-center mt-5">
								<canvas id="achievement_chart" style="max-width: 480px; max-height: 240px;"></canvas>
								<div class="flex flex-col justify-around">
									<div class="text-center bg-blue-100 rounded p-4">
										<p>目標日</p>
										<p><%= checkGoal.getDateEndToString() %></p>
									</div>
									<div class="text-center bg-orange-100 rounded p-4">
										<p>目標日まで</p>
										<p>あと<%= checkGoal.getRemainingDays() %>日！</p>
									</div>
								</div>
							</div>
							<div class="flex justify-center mt-5">
								<canvas id="material_chart" style="max-width: 480px; max-height: <%= 80 * goalDetails.size()%>px;"></canvas>
							</div>

							<script>
								var ctx = document.getElementById('material_chart');

								var data = {
									labels: [<%for(GoalDetail goalDetail: goalDetails){%>"<%= mapOfGoalDetailsAndMaterial.get(goalDetail).getMaterialName() %>",<%}%>],
									datasets: [{
										label: '達成度',
										data: [<%for(GoalDetail goalDetail: goalDetails){%>"<%= mapOfGoalDetailsAndAchievmentRatio.get(goalDetail.getMaterialId()) %>",<%}%>],
										backgroundColor: 'rgba(100, 230, 150, 1)'
									}]
								};

								var options = {
									indexAxis: 'y',
									scales: {
										x:{
											min: 0,
											max: 100
											//beginAtZero: true
										}
									}
								};

								var material_chart = new Chart(ctx, {
									type: 'bar',
									data: data,
									options: options
								});

								var ctx = document.getElementById('achievement_chart');
								var sum = 0;
								<%for(GoalDetail goalDetail: goalDetails){%> sum += <%= mapOfGoalDetailsAndAchievmentRatio.get(goalDetail.getMaterialId()) %>;<%}%>
								var achievement_ratio = sum/<%if(goalDetails.size()!=0){%><%= goalDetails.size() %><%}%><%else{%><%=1%><%}%>;
								var data = {
									labels: ["達成", "残り"],
									datasets: [{
										data: [achievement_ratio, 100-achievement_ratio],
										backgroundColor: ['rgba(100, 230, 150, 1)', 'rgba(255, 255, 255, 1)'],
										borderColor: ['rgba(75, 215, 125, 1)', 'rgba(230, 230, 230, 1)']
									}]
								};

								var options = {

								};

								var material_chart = new Chart(ctx, {
									type: 'doughnut',
									data: data,
									options: options
								});
							
							</script>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>