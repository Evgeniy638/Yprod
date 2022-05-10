package com.example.backend.service;

import com.example.backend.entities.Project;
import com.example.backend.entities.User;
import com.example.backend.entities.UserProgress;

/**
 * Сервис, выполняющий логику, связанную с выдачей пользователю достижений, повышением уровня
 *
 * @author Danil Kuzin
 */
public class UserProgressService {

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
