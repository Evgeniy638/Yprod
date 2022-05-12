package com.example.backend.service;

import com.example.backend.entities.Project;
import com.example.backend.entities.User;
import com.example.backend.entities.UserProgress;
import com.example.backend.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

/**
 * Сервис, выполняющий логику, связанную с выдачей пользователю достижений, повышением уровня
 *
 * @author Danil Kuzin
 */
@Service
@RequiredArgsConstructor
public class UserProgressService {

    @SneakyThrows
    public Long getPointsToLevelUp(UserProgress userProgress) {
        if (userProgress == null || userProgress.getProject() == null) {
            throw new NotFoundException();
        }
        return userProgress.getProject().getPointsToLevelUp() * Math.round(Math.pow(2, (userProgress.getLevel() - 1)));
    }

    /**
     * Добавляет сущность для учета прогресса пользователя на проекте
     *
     * @param project - проект
     * @param user    - пользователь
     * @return Успешность добавления
     */
    public Boolean addUserProgress(Project project, User user) {
        return true;
    }

    /**
     * Находит сущность для учета прогресса пользователя на проекте
     *
     * @param project - проект
     * @param user    - пользователь
     * @return Сущность для учета прогресса пользователя на проекте
     */
    public UserProgress findUserProgressByProjectAndUser(Project project, User user) {
        return new UserProgress();
    }

    /**
     * Выдает достижение пользователю, при необходимости повышает уровень пользователя
     *
     * @param project - проект
     * @param user    - пользователь
     * @return Успешность выдачи достижения
     */
    public Boolean giveAchievementToUser(Project project, User user) {
        return true;
    }
}
