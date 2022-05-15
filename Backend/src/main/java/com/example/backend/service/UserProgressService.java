package com.example.backend.service;

import com.example.backend.dto.request.AssignAndDepriveAchievementRequest;
import com.example.backend.entities.Achievement;
import com.example.backend.entities.Project;
import com.example.backend.entities.User;
import com.example.backend.entities.UserProgress;
import com.example.backend.exceptions.BadRequestException;
import com.example.backend.exceptions.NotFoundException;
import com.example.backend.repo.UserProgressRepo;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Сервис, выполняющий логику, связанную с выдачей пользователю достижений, повышением уровня
 *
 * @author Danil Kuzin
 */
@Service
public class UserProgressService {

    private final ProjectService projectService;

    private final UserService userService;

    private final AchievementService achievementService;

    public UserProgressService(ProjectService projectService, @Lazy UserService userService, AchievementService achievementService, UserProgressRepo userProgressRepo) {
        this.projectService = projectService;
        this.userService = userService;
        this.achievementService = achievementService;
        this.userProgressRepo = userProgressRepo;
    }

    private final UserProgressRepo userProgressRepo;

    @SneakyThrows
    public Long getPointsToLevelUp(UserProgress userProgress) {
        if (userProgress == null || userProgress.getProject() == null) {
            throw new NotFoundException();
        }
        return userProgress.getProject().getPointsToLevelUp() * Math.round(Math.pow(2, (userProgress.getLevel() - 1)));
    }

    @SneakyThrows
    public Long getLevelDownPoints(UserProgress userProgress) {
        if (userProgress == null || userProgress.getProject() == null) {
            throw new NotFoundException();
        }
        return userProgress.getProject().getPointsToLevelUp() * Math.round(Math.pow(2, (userProgress.getLevel() - 2)));
    }

    @SneakyThrows
    public Project getProjectByAdminAndUser(OidcUser admin, User user) {
        Project project = projectService.getProjectByAdmin(userService.findUserByOidcUser(admin).orElseThrow(NotFoundException::new)).orElseThrow(NotFoundException::new);
        if (project.getUsers().stream().anyMatch(projectUser -> projectUser.getId().equals(user.getId()))) {
            return project;
        }
        throw new NotFoundException();
    }

    @SneakyThrows
    public void assignAchievement(OidcUser admin, Long achievementId, AssignAndDepriveAchievementRequest request) {
        User user = userService.findUserByEmail(request.getEmail()).orElseThrow(NotFoundException::new);
        Project project = getProjectByAdminAndUser(admin, user);
        if (project.getAchievements().stream().noneMatch(achievementFromSet -> achievementFromSet.getId().equals(achievementId))) {
            throw new BadRequestException();
        }
        Achievement achievement = achievementService.getAchievement(achievementId).orElseThrow(NotFoundException::new);
        UserProgress userProgress = userProgressRepo.findFirstByUser(user).orElse(createUserProgress(project, user));
        Set<Achievement> achievementSet = userProgress.getAchievements();
        if (achievementSet.stream().noneMatch(achievementFromSet -> achievementFromSet.getId().equals(achievementId))) {
            achievementSet.add(achievement);
            userProgress.setAchievements(achievementSet);
            long pointsSum = userProgress.getPoints() + achievement.getPoints();
            long pointToLevelUp = getPointsToLevelUp(userProgress);
            if (pointsSum >= pointToLevelUp) {
                userProgress.setLevel(userProgress.getLevel() + 1);
                userProgress.setPoints(pointsSum - pointToLevelUp);
            } else {
                userProgress.setPoints(pointsSum);
            }
            userProgressRepo.save(userProgress);
        }
    }

    @SneakyThrows
    public void depriveAchievement(OidcUser admin, Long achievementId, AssignAndDepriveAchievementRequest request) {
        User user = userService.findUserByEmail(request.getEmail()).orElseThrow(NotFoundException::new);
        Project project = getProjectByAdminAndUser(admin, user);
        UserProgress userProgress = userProgressRepo.findFirstByUser(user).orElse(createUserProgress(project, user));
        Set<Achievement> achievementSet = userProgress.getAchievements();
        Achievement achievement = achievementSet.stream()
                .filter((achievementFromSet) -> achievementFromSet.getId().equals(achievementId))
                .findAny()
                .orElseThrow(BadRequestException::new);
        achievementSet.remove(achievement);
        userProgress.setAchievements(achievementSet);
        long pointsSum = userProgress.getPoints() - achievement.getPoints();
        if (pointsSum < 0) {
            userProgress.setLevel(userProgress.getLevel() - 1);
            userProgress.setPoints(pointsSum + getLevelDownPoints(userProgress));
        } else {
            userProgress.setPoints(pointsSum);
        }
        userProgressRepo.save(userProgress);
    }

    public UserProgress createUserProgress(Project project, User user) {
        UserProgress userProgress = new UserProgress();
        userProgress.setUser(user);
        userProgress.setProject(project);
        userProgress.setLevel(1L);
        userProgress.setPoints(0L);
        userProgress.setAchievements(new HashSet<>());
        return userProgressRepo.save(userProgress);
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
