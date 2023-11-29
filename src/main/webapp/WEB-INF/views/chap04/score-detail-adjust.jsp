<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- reset css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">

    <!-- bootstrap css -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">


    <!-- bootstrap js -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" defer></script>

    <!-- jquery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <style>
        section.score-main {
            width: 30%;
            margin: 0 auto 150px;
            padding: 20px;
            border: 2px solid orange;
            border-radius: 10px;
            box-shadow: 2px 2px 5px orangered;
            transform: translateY(200px);
        }

        .adjust-clear-btn {
            display: block;
            width: fit-content;
            text-decoration: none;
            background: rgb(83, 189, 83);
            color: white;
            box-shadow: 1px 1px 2px rgb(146, 228, 146);
            border-radius: 5px;
            border: 1px solid white;
            padding: 5px;
        }
        a.prev-btn{
            display: block;
            width: fit-content;
            text-decoration: none;
            background: rgb(232, 247, 93);
            color: black;
            box-shadow: 1px 1px 2px rgb(146, 228, 146);
            margin-left: 10px;
            border-radius: 5px;
            border: 1px solid white;
            padding: 5px;
        }
    </style>
</head>
<body>
<div class="wrap">
    <section class="score-main">
        <h1>${s.name}님 성적 정보</h1>
        <form action="/score/adjustDetailOne" method="POST">
                <label>
                    # 국어: <input type="text" name="kor" value="${s.kor}">
                </label>
                <label>
                    # 영어: <input type="text" name="eng" value="${s.eng}">
                </label>
                <label>
                    # 수학: <input type="text" name="math" value="${s.math}">
                </label>
            <label>
                <button type="submit" class="adjust-clear-btn" name="stuNum" value="${s.stuNum}">수정완료</button>
            </label>
        </form>
        <div class="btn-group">
            <a class="prev-btn" href="/score/detail?stuNum=${s.stuNum}">이전으로</a>
        </div>
    </section>
</div>
</body>
</html>