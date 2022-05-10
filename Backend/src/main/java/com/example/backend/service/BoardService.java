package com.example.backend.service;

import com.example.backend.entities.Board;
import com.example.backend.entities.Project;
import com.example.backend.entities.TaskStatus;
import com.example.backend.entities.enums.BoardStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис, выполняющий логику, связанную с досками задач
 *
 * @author Danil Kuzin
 */
public class BoardService {

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
    public Board getBoard(Long id) {
        return new Board();
    }

    /**
     * Возвращает список досок проекта
     *
     * @param projectId - id проекта
     * @return Список досок
     */
    public List<Board> findBoardsByProject(Long projectId) {
        return new ArrayList<>();
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
