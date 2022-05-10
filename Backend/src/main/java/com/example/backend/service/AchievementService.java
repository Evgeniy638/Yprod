package com.example.backend.service;

import com.example.backend.entities.Achievement;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис, выполняющий логику, связанную с достижениями
 *
 * @author Danil Kuzin
 */
public class AchievementService {

    /**
     * Добавляет новое достижение в базу
     *
     * @param name        - название достижения
     * @param description - описание достижения
     * @param picture     - название файла с изображением
     * @param points      - очки за получение достижения
     * @return Успешность добавления достижения
     */
    public Boolean createAchievement(String name, String description, String picture, Integer points) {
        return true;
    }

    /**
     * Возвращает список достижений по названию
     *
     * @param name - полное или частичное название достижения
     * @return Список достижений
     */
    public List<Achievement> findAchievementsByName(String name) {
        return new ArrayList<>();
    }

}
