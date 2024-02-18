<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="
	model.User,
	model.Material,
	model.Progress,
	java.text.SimpleDateFormat,
	java.util.Date
	" %>
<%
//セッションスコープに保存されたユーザー情報を取得
User user = (User)session.getAttribute("user");
//リクエストスコープに保存された目標情報を取得
Progress progress = (Progress)request.getAttribute("progress");

SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
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
								<div class="flex justify-between">
									<p class="">${ user.name }さんの記録</p>
									<p class="text-sm"><%= sdf.format(progress.getDate()) %></p>
								</div>
									<p class="text-xl mt-2 mb-4">${ material.materialName }</p>
									<p>取り組み量</p>
									<p class="mb-2">${ progress.pageStart }～${ progress.pageEnd }ページ</p>
									<p>取り組み時間</p>
									<p class="mb-2"><%= progress.getHour() %>時間<%= progress.getMunite() %>分</p>
							</div>
							<p class="text-xl text-center mt-4 mb-2">記録完了！</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>