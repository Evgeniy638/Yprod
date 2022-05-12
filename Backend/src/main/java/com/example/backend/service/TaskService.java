package com.example.backend.service;

import com.example.backend.dto.response.TaskShortResponse;
import com.example.backend.entities.Board;
import com.example.backend.entities.Task;
import com.example.backend.entities.TaskStatus;
import com.example.backend.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис, выполняющий логику, связанную с задачами
 *
 * @author Danil Kuzin
 */
@Service
@RequiredArgsConstructor
public class TaskService {

    private final UserService userService;

    public TaskShortResponse buildTaskShortResponse(Task task){
        return new TaskShortResponse()
                .setId(task.getId())
                .setName(task.getName())
                .setStatusId(task.getStatus().getId())
                .setStoryPoints(task.getStoryPoints())
                .setExecutor(userService.buildUserShortResponse(task.getExecutor()));
    }

    /**
     * Создает задачу на доске
     *
     * @param board       - доска
     * @param name        - название задачи
     * @param storyPoints - стори пойты
     * @return Успешность создания задачи
     */
    public Boolean createTask(Board board, String name, Integer storyPoints) {
        return true;
    }

    /**
     * Изменяет описание задачи
     *
     * @param task        - задача
     * @param description - новое описание задачи
     * @param creator     - создатель задачи
     * @return Успешность изменения
     */
    public Boolean changeDescription(Task task, String description, User creator) {
        return true;
    }

    /**
     * Добавляет комментарий к задаче
     *
     * @param task - задача
     * @param text - текст комментария
     * @param user - автор комментария
     * @return Успешность добавления
     */
    public Boolean addComment(Task task, String text, User user) {
        return true;
    }

    /**
     * Изменяет исполнителя задачи
     *
     * @param task     - задача
     * @param executor - новый исполнитель
     * @return Успешность изменения
     */
    public Boolean changeExecutor(Task task, User executor) {
        return true;
    }

    /**
     * Добавляет вложение к задаче
     *
     * @param task     - задача
     * @param fileName - название файла
     * @return Успешность добавления
     */
    public Boolean addAttachment(Task task, String fileName) {
        return true;
    }

    /**
     * Изменяет статус задачи
     *
     * @param task       - задача
     * @param taskStatus - новый статус
     * @return Успешность изменения
     */
    public Boolean changeStatus(Task task, TaskStatus taskStatus) {
        return true;
    }

    /**
     * Возвращает задачи на доске
     *
     * @param boardId - id доски
     * @return Список задач
     */
    public List<Task> findTasksByBoard(Long boardId) {
        return new ArrayList<>();
    }

    /**
     * Возвращает задачу по id
     *
     * @param id - id задачи
     * @return Задача
     */
    public Task getTask(Long id) {
        return new Task();
    }
}
