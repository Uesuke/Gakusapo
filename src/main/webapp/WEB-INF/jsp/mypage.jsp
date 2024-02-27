<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User, model.Progress, model.Material, java.util.List, java.util.Map" %>
<%
//セッションスコープに保存されたユーザー情報を取得
User user = (User)session.getAttribute("user");

//セッションスコープに保存されたProgressリストを取得
List<Progress> progressList = (List<Progress>)session.getAttribute("progressList");
Map<Progress, Material> map = (Map<Progress, Material>)session.getAttribute("MapOfProgressAndMaterial");
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
		<div class="container w-[1024px] mx-auto mt-5">
			<div class="flex justify-center">
				<div class="w-full">
					<h1 class="text-3xl text-center mb-8">ホーム画面</h1>
					<div class="bg-white p-8 rounded shadow-lg">
						<div class="w-[612px] mx-auto">
							<h2 class="text-2xl mb-1">Profile</h2>
							<p>お名前：<%= user.getName() %>さん</p>
							<p>アカウントID:<%= user.getAccountId() %></p>
					    </div>
					    <div class="w-[612px] mx-auto mt-5">
					    	<h2 class="text-2xl">Records</h2>
							<ul class="bg-gray-50 w-[612px] h-[612px] mx-auto overflow-y-scroll border border-gray-300 shadow">
								<% for(Progress progress : progressList){ %>
									<li class="container flex justify-between bg-white border-b p-2">
										<div class="">
											<h2 class="font-medium mb-1"><%= map.get(progress).getMaterialName() %></h2>
											<p class="mb-1 ml-2">時間：<%= progress.getHour() %>時間<%= progress.getMunite() %>分</p>
											<p class="mb-1 ml-2">範囲：<%= progress.getPageStart() %>～<%= progress.getPageEnd() %>ページ（計<%= progress.getPageEnd() - progress.getPageStart() %>ページ）</p>
										</div>
										<div class="text-sm">
											<p><%= progress.getDateToString() %></p>
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