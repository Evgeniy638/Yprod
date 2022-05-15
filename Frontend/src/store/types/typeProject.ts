import { Achievement } from './typeUser';

export interface Project {
    id: number;
    name: string;
    description: string;
    achievements: Achievement[];
}

export interface StateProject {
    project?: Project | undefined;
}

export enum ListTypeProjectActions {
    SET_CURRENT_PROJECT = 'SET_CURRENT_PROJECT',
    ADD_ACHIEVEMENT_TO_PROJECT = 'ADD_ACHIEVEMENT_TO_PROJECT',
}

interface SetCurrentProject {
    type: ListTypeProjectActions.SET_CURRENT_PROJECT;
    project: Project;
}

interface AddAchievementToProject {
    type: ListTypeProjectActions.ADD_ACHIEVEMENT_TO_PROJECT;
    achievement: Achievement;
}

export type ActionProject = SetCurrentProject
    | AddAchievementToProject;
