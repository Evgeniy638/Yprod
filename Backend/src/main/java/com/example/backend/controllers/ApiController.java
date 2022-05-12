package com.example.backend.controllers;

import com.example.backend.dto.request.CreateBoardRequest;
import com.example.backend.dto.request.GrantAccessRequest;
import com.example.backend.dto.response.*;
import com.example.backend.exceptions.NotFoundException;
import com.example.backend.exceptions.ServerException;
import com.example.backend.service.AchievementService;
import com.example.backend.service.BoardService;
import com.example.backend.service.ProjectService;
import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final UserService userService;

    private final AchievementService achievementService;

    private final BoardService boardService;

    private final ProjectService projectService;

    @GetMapping("/me")
    public ResponseEntity<GetUserResponse> getUser(@AuthenticationPrincipal OidcUser user) {
        try {
            return new ResponseEntity<>(userService.buildGetUserResponse(user), HttpStatus.OK);
        } catch (Exception e) {
            return getResponseEntityByException(e);
        }
    }

    @GetMapping("/me/personal")
    public ResponseEntity<GetUserAchievementsResponse> getUserAchievements(@AuthenticationPrincipal OidcUser user) {
        try {
            return new ResponseEntity<>(userService.buildGetUserAchievementsResponse(user), HttpStatus.OK);
        } catch (Exception e) {
            return getResponseEntityByException(e);
        }
    }

    @GetMapping("/achievement/{id}/picture")
    public ResponseEntity<GetAchievementPictureResponse> getAchievementPicture(@AuthenticationPrincipal OidcUser user, @PathVariable("id") long id) {
        try {
            userService.validateAccessToProject(user, achievementService.getProjectByAchievementId(id));
            return new ResponseEntity<>(achievementService.buildGetAchievementPictureResponse(id), HttpStatus.OK);
        } catch (Exception e) {
            return getResponseEntityByException(e);
        }
    }

    @GetMapping("/project/{id}/board")
    public ResponseEntity<GetBoardsResponse> getBoards(@AuthenticationPrincipal OidcUser user, @PathVariable("id") long id) {
        try {
            userService.validateAccessToProject(user, projectService.getProject(id).orElseThrow(NotFoundException::new));
            return new ResponseEntity<>(boardService.buildGetBoardsResponse(id), HttpStatus.OK);
        } catch (Exception e) {
            return getResponseEntityByException(e);
        }
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<GetBoardInfoResponse> getBoard(@AuthenticationPrincipal OidcUser user, @PathVariable("id") long id) {
        try {
            userService.validateAccessToProject(user, boardService.getProjectByBoardId(id));
            return new ResponseEntity<>(boardService.buildGetBoardInfoResponse(id), HttpStatus.OK);
        } catch (Exception e) {
            return getResponseEntityByException(e);
        }
    }

    @PostMapping("/project/access")
    public ResponseEntity<Void> grantAccess(@AuthenticationPrincipal OidcUser user, GrantAccessRequest request){
        try {
            userService.validateAdminOfProject(user, projectService.getProject(request.getProjectId()).orElseThrow(NotFoundException::new));
            projectService.grandAccess(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return getResponseEntityByException(e);
        }
    }

    @PostMapping("/board")
    public ResponseEntity<CreateBoardResponse> createBoard(@AuthenticationPrincipal OidcUser user, CreateBoardRequest request){
        try {
            userService.validateAdminOfProject(user, projectService.getProject(request.getProjectId()).orElseThrow(NotFoundException::new));
            return new ResponseEntity<>(boardService.create(request), HttpStatus.OK);
        } catch (Exception e) {
            return getResponseEntityByException(e);
        }
    }

    private <T> ResponseEntity<T> getResponseEntityByException(Exception e) {
        log.error("Server Exception " + e.getMessage());
        if (e instanceof ServerException) {
            return new ResponseEntity<T>(((ServerException) e).getHttpStatus());
        }
        return new ResponseEntity<T>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
