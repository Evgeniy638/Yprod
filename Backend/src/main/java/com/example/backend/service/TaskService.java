package com.example.backend.service;

import com.example.backend.dto.request.CreateTaskRequest;
import com.example.backend.dto.request.EditTaskRequest;
import com.example.backend.dto.response.TaskResponse;
import com.example.backend.dto.response.TaskShortResponse;
import com.example.backend.entities.*;
import com.example.backend.exceptions.NotFoundException;
import com.example.backend.repo.TaskRepo;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Сервис, выполняющий логику, связанную с задачами
 *
 * @author Danil Kuzin
 */
@Service
public class TaskService {

    private final UserService userService;

    private final BoardService boardService;

    private final TaskRepo taskRepo;

    private final TaskStatusService taskStatusService;

    public TaskService(UserService userService, @Lazy BoardService boardService, TaskRepo taskRepo, TaskStatusService taskStatusService) {
        this.userService = userService;
        this.boardService = boardService;
        this.taskRepo = taskRepo;
        this.taskStatusService = taskStatusService;
    }

    @SneakyThrows
    public Project getProjectByTaskId(Long id) {
        return getTask(id).orElseThrow(NotFoundException::new).getBoard().getProject();
    }

    @SneakyThrows
    public TaskResponse getTaskResponse(Long id) {
        return buildTaskResponse(getTask(id).orElseThrow(() -> new NotFoundException("")));
    }

    @SneakyThrows
    public TaskResponse edit(Long taskId, EditTaskRequest request) {
        Task task = getTask(taskId).orElseThrow(NotFoundException::new);
        if (request.getExecutorEmail() == null || "".equals(request.getExecutorEmail())) {
            task.setExecutor(task.getCreator());
        } else {
            task.setExecutor(userService.findUserByEmail(request.getExecutorEmail()).orElseThrow(NotFoundException::new));
        }

        if (request.getStatusId() != null) {
            TaskStatus status = taskStatusService.getTaskStatus(request.getStatusId()).orElseThrow(NotFoundException::new);
            taskStatusService.getBoardTaskStatusByBoardAndTaskStatus(task.getBoard(), status).orElseThrow(NotFoundException::new);
            task.setStatus(status);
        }

        if (request.getName() != null) {
            task.setName(request.getName());
        }

        if (request.getDescription() != null) {
            task.setDescription(request.getDescription());
        }

        if (request.getStoryPoints() != null) {
            task.setStoryPoints(request.getStoryPoints());
        }

        task = taskRepo.save(task);

        return buildTaskResponse(task);
    }

    @SneakyThrows
    public TaskResponse create(OidcUser oidcUser, Long boardId, CreateTaskRequest request) {
        Board board = boardService.getBoard(boardId).orElseThrow(NotFoundException::new);
        Task task = new Task();
        User creator = userService.findUserByOidcUser(oidcUser).orElseThrow(NotFoundException::new);
        if (request.getExecutorEmail() == null || "".equals(request.getExecutorEmail())) {
            task.setExecutor(creator);
        } else {
            task.setExecutor(userService.findUserByEmail(request.getExecutorEmail()).orElseThrow(NotFoundException::new));
        }
        List<BoardTaskStatus> boardTaskStatusList = taskStatusService.getBoardTaskStatusesByBoard(board);
        if (boardTaskStatusList.isEmpty()) {
            throw new NotFoundException();
        }
        task.setStatus(boardTaskStatusList.get(0).getTaskStatus());
        task.setCreator(creator);
        task.setName(request.getName());
        task.setDescription(request.getDescription());
        task.setStoryPoints(request.getStoryPoints());
        task.setBoard(board);
        task = taskRepo.save(task);
        return buildTaskResponse(task);
    }

    private TaskResponse buildTaskResponse(Task task) {
        return new TaskResponse()
                .setId(task.getId())
                .setName(task.getName())
                .setDescription(task.getDescription())
                .setStoryPoints(task.getStoryPoints())
                .setStatus(task.getStatus().getValue())
                .setBoardId(task.getBoard().getId())
                .setExecutor(userService.buildUserShortResponse(task.getExecutor()));
    }

    public TaskShortResponse buildTaskShortResponse(Task task) {
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
    public Optional<Task> getTask(Long id) {
        return taskRepo.findById(id);
    }
}
