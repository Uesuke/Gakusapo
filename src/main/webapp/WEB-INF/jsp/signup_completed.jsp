<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
					<h1 class="text-3xl text-center mb-8">登録完了！</h1>
					<div class="bg-white p-8 rounded shadow-lg">
						<form action="LoginServlet" method="post">
							<div class="mb-6">
								<label for="accountId">ユーザーID</label>
								<input type="id" class="form-input w-40 border-2 border-gray-500" name="accountId">
							</div>
							<div class="mb-6">
								<label for="pass">パスワード</label>
								<input type="password" class="form-input w-40 border-2 border-gray-500" name="pass">
							</div>
							<button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
								ログイン
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
    </body>
</html>