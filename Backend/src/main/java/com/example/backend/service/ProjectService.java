package com.example.backend.service;

import com.example.backend.dto.enums.UserProjectRole;
import com.example.backend.dto.request.GrantAccessRequest;
import com.example.backend.dto.response.ProjectResponse;
import com.example.backend.dto.response.ProjectShortResponse;
import com.example.backend.entities.Achievement;
import com.example.backend.entities.Project;
import com.example.backend.entities.User;
import com.example.backend.exceptions.NotFoundException;
import com.example.backend.repo.ProjectRepo;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Сервис, выполняющий логику, связанную с проектами
 *
 * @author Danil Kuzin
 */
@Service
public class ProjectService {

    private final ProjectRepo projectRepo;

    private final UserService userService;

    private final AchievementService achievementService;

    public ProjectService(ProjectRepo projectRepo, @Lazy UserService userService, AchievementService achievementService) {
        this.projectRepo = projectRepo;
        this.userService = userService;
        this.achievementService = achievementService;
    }

    @SneakyThrows
    public void grandAccess(GrantAccessRequest request) {
        User user = userService.findUserByEmail(request.getEmail()).orElseThrow(NotFoundException::new);
        Project project = getProject(request.getProjectId()).orElseThrow(NotFoundException::new);
        Set<User> projectUsers = project.getUsers();
        projectUsers.add(user);
        project.setUsers(projectUsers);
        if (projectUsers.containsAll(project.getUsers())) {
            return;
        }
        projectRepo.save(project);
    }

    @SneakyThrows
    public ProjectShortResponse buildProjectShortResponse(Project project) {
        if (project == null) {
            throw new NotFoundException();
        }
        return new ProjectShortResponse()
                .setId(project.getId())
                .setName(project.getName());
    }

    @SneakyThrows
    public ProjectResponse buildProjectResponse(Long id) {
        Project project = getProject(id).orElseThrow(NotFoundException::new);
        return new ProjectResponse()
                .setId(project.getId())
                .setName(project.getName())
                .setDescription(project.getDescription())
                .setAchievements(project.getAchievements().stream()
                        .map(achievementService::buildAchievementResponse)
                        .collect(Collectors.toList())
                );
    }

    @SneakyThrows
    public UserProjectRole getUserProjectRole(User user, Project project) {
        if (user == null || project == null) {
            throw new NotFoundException();
        }
        if (project.getAdmins().contains(user)) {
            return UserProjectRole.PROJECT_ADMIN;
        }
        return UserProjectRole.PROJECT_USER;
    }

    public Optional<Project> getProjectByAdmin(User user) {
        return projectRepo.findFirstByAdminsContains(user);
    }

    public Optional<Project> getProjectByUser(User user) {
        return projectRepo.findFirstByUsersContains(user);
    }


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
    public Optional<Project> getProject(Long id) {
        return projectRepo.findById(id);
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
