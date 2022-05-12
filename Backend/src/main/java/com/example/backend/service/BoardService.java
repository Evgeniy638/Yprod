package com.example.backend.service;

import com.example.backend.dto.request.CreateBoardRequest;
import com.example.backend.dto.response.BoardShortResponse;
import com.example.backend.dto.response.CreateBoardResponse;
import com.example.backend.dto.response.GetBoardInfoResponse;
import com.example.backend.dto.response.GetBoardsResponse;
import com.example.backend.entities.Board;
import com.example.backend.entities.Project;
import com.example.backend.entities.TaskStatus;
import com.example.backend.entities.enums.BoardStatus;
import com.example.backend.exceptions.NotFoundException;
import com.example.backend.repo.BoardRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис, выполняющий логику, связанную с досками задач
 *
 * @author Danil Kuzin
 */
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepo boardRepo;

    private final TaskStatusService taskStatusService;

    private final TaskService taskService;

    private final ProjectService projectService;

    @SneakyThrows
    public CreateBoardResponse create(CreateBoardRequest request){
        Board board = new Board();
        Project project = projectService.getProject(request.getProjectId()).orElseThrow(NotFoundException::new);
        board.setName(request.getName());
        board.setDescription(request.getDescription());
        board.setProject(project);
        board = boardRepo.save(board);
        return new CreateBoardResponse()
                .setBoardId(board.getId())
                .setName(board.getName())
                .setDescription(board.getDescription());
    }

    @SneakyThrows
    public Project getProjectByBoardId(Long id){
        Board board = getBoard(id).orElseThrow(NotFoundException::new);
        return board.getProject();
    }

    public GetBoardsResponse buildGetBoardsResponse(Long projectId) {
        return new GetBoardsResponse().setBoards(
                findBoardsByProjectId(projectId).stream()
                        .map(this::buildBoardShortResponse)
                        .collect(Collectors.toList())

        );
    }

    public BoardShortResponse buildBoardShortResponse(Board board) {
        return new BoardShortResponse()
                .setId(board.getId())
                .setName(board.getName());
    }

    @SneakyThrows
    public GetBoardInfoResponse buildGetBoardInfoResponse(Long boardId){
        Board board = getBoard(boardId).orElseThrow(NotFoundException::new);
        return new GetBoardInfoResponse()
                .setId(board.getId())
                .setName(board.getName())
                .setDescription(board.getDescription())
                .setStatuses(board.getBoardTaskStatuses().stream()
                        .map(taskStatusService::buildStatusResponse)
                        .collect(Collectors.toList())
                )
                .setTasks(board.getTasks().stream()
                        .map(taskService::buildTaskShortResponse)
                        .collect(Collectors.toList())
                );
    }

    /**
     * Добавляет новую доску в проект
     *
     * @param project - проект
     * @param name    - название доски
     * @return Успешность добавления
     */
    public Boolean addBoard(Project project, String name) {
        return true;
    }

    /**
     * Возвращает доску по id
     *
     * @param id - id доски
     * @return Доска
     */
    public Optional<Board> getBoard(Long id) {
        return boardRepo.findById(id);
    }

    /**
     * Возвращает список досок проекта
     *
     * @param projectId - id проекта
     * @return Список досок
     */
    public List<Board> findBoardsByProjectId(Long projectId) {
        return boardRepo.findAllByProjectId(projectId);
    }

    /**
     * Меняет статус доски
     *
     * @param board       - доска
     * @param boardStatus - статус доски
     * @return Успешность смены статуса
     */
    public Boolean changeBoardStatus(Board board, BoardStatus boardStatus) {
        return true;
    }

    /**
     * Изменяет инормацию о доске
     *
     * @param board       - доска
     * @param name        - новое название
     * @param description - новое описание
     * @return Успешность изменения
     */
    public Boolean editBoardInf(Board board, String name, String description) {
        return true;
    }

    /**
     * Добавляет статус задач на доску
     *
     * @param board      - доска
     * @param taskStatus - статус задач
     * @return Успшеность добавления
     */
    public Boolean addTaskStatus(Board board, TaskStatus taskStatus) {
        return true;
    }

}
