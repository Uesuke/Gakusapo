<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Top|Study-with-You</title>
        <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <body class="bg-gray-100">
	 <div class="container mx-auto mt-5">
        <div class="flex justify-center">
            <div class="w-full max-w-lg">
				<h1 class="text-3xl text-center mb-8">トップ画面</h1>
				<div class="bg-white p-8 rounded shadow-lg">
                <ul>
                    <li>
	                    <form action="LoginServlet" method="get">
		                	<button class="btn btn-secondary mt-3">ログイン</button>
		            	</form>
                    </li>
                    <li>
	                    <form action="SignupServlet" method="get">
		                	<button class="btn btn-secondary mt-3">サインアップ</button>
		            	</form>
                    </li>
                </ul>
				</div>
            </div>
        </div>
    </div>
</body>
</html>