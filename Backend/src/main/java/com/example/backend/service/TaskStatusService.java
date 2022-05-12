package com.example.backend.service;

import com.example.backend.dto.response.StatusResponse;
import com.example.backend.entities.BoardTaskStatus;
import com.example.backend.entities.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис, выполняющий логику, связанную со статусами задач
 *
 * @author Danil Kuzin
 */
@Service
public class TaskStatusService {

    public StatusResponse buildStatusResponse(BoardTaskStatus boardStatus){
        return new StatusResponse()
                .setId(boardStatus.getTaskStatus().getId())
                .setName(boardStatus.getTaskStatus().getValue())
                .setOrder(boardStatus.getOrder());
    }

    /**
     * Создает новый статус задач
     *
     * @param name - название статуса
     * @return Успешность создания
     */
    public Boolean addTaskStatus(String name) {
        return true;
    }

    /**
     * Находит статусы задач по названию
     *
     * @param name - частичное или полное название статуса
     * @return Список статусов
     */
    public List<TaskStatus> findTaskStatusByName(String name) {
        return new ArrayList<>();
    }
}
