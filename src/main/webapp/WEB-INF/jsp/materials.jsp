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
		<title>Library_Material|GAKUSAPO</title>
		<script src="https://cdn.tailwindcss.com"></script>
	</head>
	<body class="bg-gray-100">
		<jsp:include page="../../header.jsp"/>
		<div class="container w-[1024px] mx-auto mt-5">
			<div class="flex justify-center">
				<div class="w-full max-w-5xl">
					<div class="bg-white p-8 rounded shadow-lg">
						<div id="content" class="m-auto">
                            <h1 class="text-xl text-center">教材一覧</h1>
							<ul class="flex justify-start flex-wrap mt-5">
								<% for(Material material : materialList){ %>
									<li class="w-[200px] px-2 py-4 mx-5 my-6 border border-gray-300 rounded text-center shadow">
										<h2 class="font-bold"><%= material.getMaterialName() %></h2>
										<p><%= material.getTotalPages() %>ページ</p>
										<%if(material.getSectionStart() != 0 && material.getSectionEnd() != 0) { %>
										<p><%= material.getTotalSections() %>単位</p>
										<% } %>
										<p>総取り組み時間</p>
										<p>HH時間mm分</p>
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