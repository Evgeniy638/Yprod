import { Achievement } from '../types/typeUser';
import { ActionProject, ListTypeProjectActions, Project } from './../types/typeProject';

export const setCurrentProject = (project: Project): ActionProject => ({
    type: ListTypeProjectActions.SET_CURRENT_PROJECT,
    project,
});

export const addAchievementToProject = (achievement: Achievement): ActionProject => ({
    type: ListTypeProjectActions.ADD_ACHIEVEMENT_TO_PROJECT,
    achievement,
});
