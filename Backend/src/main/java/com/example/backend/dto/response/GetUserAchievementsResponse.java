package com.example.backend.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
public class GetUserAchievementsResponse {

    private List<AchievementResponse> achievements;

}
