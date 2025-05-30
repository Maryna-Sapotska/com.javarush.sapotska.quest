<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
  <title>Квест</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- Подключение CSS Bootstrap -->
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<div class="container mt-3 mb-3 ml-5">
  <h1 class="text-center">Добро пожаловать! ${playerName}</h1>
</div>
<div class="container mt-3 mb-3 ml-3">
  <p>Далеко в древнем королевстве, где магия и тайны переплетаются с реальностью, происходит странное событие. Пророчество гласят, что великий герой должен пройти через леса и города, чтобы спасти королевство от неминуемой угрозы.</p>
  <p>Вы — избранный! Вам предстоит пройти через опасные испытания и сделать важный выбор, который повлияет на судьбу мира.</p>
  <p>Будьте осторожны, и удачи вам в этом захватывающем путешествии!</p>
  <form action="game-servlet" method="post">
    <label for="playerName">Введите ваше имя:</label>
    <input type="text" id="playerName" name="playerName" required/>
    <button type="submit">Начать игру</button>
  </form>
</div>

<!-- Подключение JS Bootstrap -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
