<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User, model.Goal,java.util.List" %>
<%
//セッションスコープに保存されたユーザー情報を取得
User user = (User)session.getAttribute("user");
//セッションスコープに保存されたMaterialリストを取得
List<Goal> goalsList = (List<Goal>)session.getAttribute("goalsList");
%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Goals|GAKUSAPO</title>
		<script src="https://cdn.tailwindcss.com"></script>
	</head>
	<body class="bg-gray-100">
		<jsp:include page="../../header.jsp"/>
		<div class="container w-[1024px] mx-auto mt-5">
			<div class="flex justify-center">
				<div class="w-full max-w-5xl">
					<div class="bg-white p-8 rounded shadow-lg">
						<div id="content" class="m-auto">
                            <h1 class="text-xl text-center">目標一覧</h1>
							<ul class="flex flex-col justify-start mt-5">
								<% for(Goal goal : goalsList){ %>
									<li class="flex justify-between w-2/3 h-[120px] px-2 mx-auto my-6 border border-gray-300 rounded shadow">
										<div>
											<h2 class="text-xl mt-2 font-bold"><%= goal.getGoalName() %></h2>
											<p class="mt-3 ml-4">目標日：<%= goal.getDateEnd() %></p>
											<p class="mt-1 ml-4">あと<%= goal.getRemainingDays() %>日！</p>
										</div>
										<div>
											<form action="ViewAchievementServlet" method="post">
												<input type="hidden" name="goalId" value="<%= goal.getGoalId() %>">
												<button type="submit" class="bg-orange-500 hover:bg-orange-700 text-white font-bold py-2 px-4 mt-9 mr-4 rounded">
													達成度を確認
												</button>
											</form>
										</div>
									</li>
								<% } %>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>