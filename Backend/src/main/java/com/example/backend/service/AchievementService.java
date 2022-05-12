package com.example.backend.service;

import com.example.backend.dto.response.AchievementResponse;
import com.example.backend.dto.response.GetAchievementPictureResponse;
import com.example.backend.entities.Achievement;
import com.example.backend.entities.Project;
import com.example.backend.exceptions.NotFoundException;
import com.example.backend.repo.AchievementRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Сервис, выполняющий логику, связанную с достижениями
 *
 * @author Danil Kuzin
 */
@Service
@RequiredArgsConstructor
public class AchievementService {

    private final AchievementRepo achievementRepo;

    @SneakyThrows
    public Project getProjectByAchievementId(Long id){
        Achievement achievement = findAchievementById(id).orElseThrow(NotFoundException::new);
        return achievement.getProjects().stream().findFirst().orElseThrow(NotFoundException::new);
    }

    @SneakyThrows
    public AchievementResponse buildAchievementResponse(Achievement achievement) {
        if (achievement == null) {
            throw new NotFoundException();
        }
        return new AchievementResponse()
                .setId(achievement.getId())
                .setName(achievement.getName())
                .setDescription(achievement.getDescription())
                .setPoints(achievement.getPoints());
    }

    @SneakyThrows
    public GetAchievementPictureResponse buildGetAchievementPictureResponse(Long id) {
        Achievement achievement = findAchievementById(id).orElseThrow(NotFoundException::new);
        return new GetAchievementPictureResponse()
                .setPicture(achievement.getPicture());
    }


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

    private Optional<Achievement> findAchievementById(Long id) {
        return achievementRepo.findById(id);
    }

}
