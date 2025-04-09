package org.example.comjavarushsapotskaquest.repository;

import org.example.comjavarushsapotskaquest.entity.Answer;
import org.example.comjavarushsapotskaquest.entity.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameRepository {
    private final Map<Long, Question> questionMap = new HashMap<>();

    public GameRepository() {
        List<Answer> answers1 = new ArrayList<>();
        answers1.add(new Answer(1L, "Пойти в лес", 2L, false, false, true)); // Поражение
        answers1.add(new Answer(2L, "Пойти в город", 3L, false, true, false)); // Победа
        answers1.add(new Answer(3L, "Остаться на месте", 0L, true, false, false)); // Завершение игры
        questionMap.put(1L, new Question(1L, "Вы стоите на распутье. Куда пойдете?", answers1));

        List<Answer> answers2 = new ArrayList<>();
        answers2.add(new Answer(1L, "Попробовать найти безопасный обходной путь", 4L, false, true, false)); // Победа
        answers2.add(new Answer(2L, "Вернуться обратно, не замечая ничего вокруг", 1L, false, false, true)); // Поражение
        answers2.add(new Answer(3L, "Остаться в лесу", 0L, true, false, false)); // Завершение игры
        questionMap.put(2L, new Question(2L, "Вы в лесу, где древние существа скрываются в тенях. Он выглядит опасным, но вам нужно пройти через него. Как поступите?\n" +
                "\n", answers2));

        List<Answer> answers3 = new ArrayList<>();
        answers3.add(new Answer(1L, "Пойду в светлую залу, несмотря на опасности", 4L, false, true, false)); // Победа
        answers3.add(new Answer(2L, "Выберу темный путь, надеясь на скрытые знания", 0L, true, false, false)); // Поражение
        answers3.add(new Answer(3L, "Остановлюсь и поищу дополнительные подсказки", 0L, true, false, false)); // Завершение игры
        questionMap.put(3L, new Question(3L, "После долгого пути вы добираетесь до города, где находите древний храм. Внутри стоит выбор: один проход ведет в светлую,\n" +
                "\n но опасную залу, другой — в темную, полную загадок. Какой путь выберете?", answers3));

        List<Answer> answers4 = new ArrayList<>();
        answers4.add(new Answer(1L, "Вернусь обратно", 0L, true, false, false));
        answers4.add(new Answer(2L, "Зайду в таинственную дверь", 0L, true, true, false)); // Победа
        questionMap.put(4L, new Question(4L, "Вы сталкиваетесь с выбором пути. Что планируете делать дальше?", answers4));
    }

    public Question getQuestionById(Long id) {
        return questionMap.get(id);
    }
}
