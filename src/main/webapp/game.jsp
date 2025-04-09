<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Quest</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<h1 class="container mt-3 mb-3 ml-3">Добро пожаловать, ${playerName}!</h1>
<h4>
    <p class="container mt-3 mb-3 ml-3"><strong></strong> ${question.question}</p>
</h4>

<c:if test="${empty answers}">
    <p>Ответы не найдены!</p>
</c:if>

<form action="game-servlet" method="post">
    <c:forEach var="answer" items="${answers}">
        <label>
            <input type="radio" name="answerId" value="${answer.id}" /> ${answer.answer}
        </label><br>
    </c:forEach>

    <button type="submit">Отправить</button>
</form>

<!-- Подключение JS Bootstrap -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>


