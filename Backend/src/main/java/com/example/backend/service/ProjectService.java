package com.example.backend.service;

import com.example.backend.entities.Achievement;
import com.example.backend.entities.Project;
import com.example.backend.entities.User;

/**
 * Сервис, выполняющий логику, связанную с проектами
 *
 * @author Danil Kuzin
 */
public class ProjectService {

    /**
     * Создает проект
     *
     * @param name - название проекта
     * @param user - создатель проекта
     * @return успешность создания проекта
     */
    public Boolean createProject(String name, User user) {
        return true;
    }

    /**
     * Возвращает проект по его id
     *
     * @param id - id проекта
     * @return Проект
     */
    public Project getProject(Long id) {
        return new Project();
    }

    /**
     * Добавляет администратора к проекту
     *
     * @param project - проект
     * @param user    - новый администратор
     * @return Успешность добавления
     */
    public Boolean addAdmin(Project project, User user) {
        return true;
    }

    /**
     * Добавляет нового участника команды проекта
     *
     * @param project - проект
     * @param user    - новый участник команды проекта
     * @return Успешность добавления
     */
    public Boolean addUser(Project project, User user) {
        return true;
    }

    /**
     * Добавлет доступное на проекте достижение
     *
     * @param project     - проект
     * @param achievement - достижение
     * @return Успешность добавления
     */
    public Boolean addAchievement(Project project, Achievement achievement) {
        return true;
    }

    /**
     * Редактирует информацию о проекте
     *
     * @param project         - проект
     * @param name            - новое название
     * @param description     - новое описание
     * @param pointsToLevelUp - новое количество очков, необходимое для достижения следующего уровня
     * @return Успешность изменения
     */
    public Boolean editProjectInfo(Project project, String name, String description, Integer pointsToLevelUp) {
        return true;
    }
}
