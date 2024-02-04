<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.SignupUser" %>
<%
    //セッションスコープにユーザー情報があれば獲得
    SignupUser signupUser = (SignupUser)session.getAttribute("signupUser");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sign-up | Study-with-You</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
    <div class="container mx-auto mt-5">
        <div class="flex justify-center">
            <div class="w-full max-w-lg">
                <h1 class="text-3xl text-center mb-8">ユーザー登録画面</h1>
                <div class="bg-white p-8 rounded shadow-lg">
                    <form action="SignupServlet" method="post">
                        <div class="mb-6">
                            <label for="accountId" class="block mb-2">新規ユーザーID</label>
                            <input type="text" class="form-input w-full border-2 border-gray-500" id="accountId" name="accountId" placeholder="IDを入力" value="<%= signupUser != null ? signupUser.getAccountId() : "" %>">
                            <% if(request.getAttribute("errorMsg_Id") != null) { %>
                                <span class="text-red-600 block mt-2"><%= request.getAttribute("errorMsg_Id") %></span>
                            <% } %>
                        </div>
                        <div class="mb-6">
                            <label for="name" class="block mb-2">名前</label>
                            <input type="text" class="form-input w-full border-2 border-gray-500" id="name" name="name" placeholder="名前を入力" value="<%= signupUser != null ? signupUser.getName() : "" %>">
                        </div>
                        <div class="mb-6">
                            <label for="pass" class="block mb-2">パスワード設定</label>
                            <input type="password" class="form-input w-full border-2 border-gray-500" id="pass" name="pass" placeholder="パスワードを入力" value="<%= signupUser != null ? signupUser.getPass() : "" %>">
                        </div>
                        <div class="mb-6">
                            <label for="pass_for_check" class="block mb-2">パスワード(確認用)</label>
                            <input type="password" class="form-input w-full border-2 border-gray-500" id="pass_for_check" name="pass_for_check" placeholder="パスワードを再度入力" value="<%= signupUser != null ? signupUser.getPass() : "" %>">
                        </div>
                        <div class="mb-6">
                            <label for="mail" class="block mb-2">メールアドレス</label>
                            <input type="email" class="form-input w-full border-2 border-gray-500" id="mail" name="mail" placeholder="メールアドレスを入力" value="<%= signupUser != null ? signupUser.getMail() : "" %>">
                        </div>
                        <div class="mb-6">
                            <label class="block mb-2">ユーザータイプ</label>
                            <div class="flex items-center mb-2">
                                <input class="form-checkbox" type="radio" name="userTypeId" id="userType1" value="1" <%= signupUser != null && signupUser.getUserTypeId() == 1 ? "checked" : "" %>>
                                <label class="ml-2" for="userType1">個人・学生モード</label>
                            </div>
                            <div class="flex items-center">
                                <input class="form-checkbox" type="radio" name="userTypeId" id="userType2" value="2" <%= signupUser != null && signupUser.getUserTypeId() == 2 ? "checked" : "" %>>
                                <label class="ml-2" for="userType2">教師モード</label>
                            </div>
                        </div>
                        <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">登録</button>
                    </form>
                </div>
                <div class="mt-3 text-center">
                    <p>※すでにアカウントをお持ちの方は<a href="LoginServlet" class="underline">こちらからログイン</a></p>
                    <p><a href="index.jsp" class=underline>トップへ</a></p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
