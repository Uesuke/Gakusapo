<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.SignupUser" %>
<%
    //セッションスコープのユーザー情報を獲得
    SignupUser signupUser = (SignupUser)session.getAttribute("signupUser");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign-up|Study-with-You</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="pt-5">
    <div id="signup_confirm" class="container">
        <div class="main text-center mt-5">
            <h1>ユーザー登録確認画面</h1>
            <div class="border rounded p-4 mb-4">
                <p>以下の登録内容に誤りがないか確認してください</p>
                <p>ユーザーID：<br>
                <%=signupUser.getAccountId() %></p>
                <p>名前：<br>
                <%=signupUser.getName() %></p>
                <p>パスワード：<br>
                <%=signupUser.getPass() %></p>
                <p>メールアドレス：<br>
                <%=signupUser.getMail() %></p>
                <p>ユーザータイプ：<br>
                <%if(signupUser.getUserTypeId() == 1){ %>
                    個人・学生モード
                <% } else if(signupUser.getUserTypeId() == 2) { %>
                    教師モード
                <% } else { %>
                    <span class="text-danger">ユーザータイプの取得に失敗しました</span>
                <% } %>
                </p>
                <form action="CompleteSignupServlet" method="post">
	                <button type="submit" class="btn btn-primary">確認して登録</button>
	            </form>
	            <form action="SignupServlet" method="get">
	                <button class="btn btn-secondary mt-3">登録内容を修正</button>
	            </form>
            </div>
        </div>
    </div>
</body>
</html>
