import { Achievement } from './typeUser';

export interface Project {
    id: number;
    name: string;
    description: string;
    achievements: Achievement[];
}

export interface StateProject {
    project?: Project;
}

export enum ListTypeProjectActions {
    SET_CURRENT_PROJECT = 'SET_CURRENT_PROJECT',
}

interface SetCurrentProject {
    type: ListTypeProjectActions.SET_CURRENT_PROJECT;
    project: Project;
}

export type ActionProject = SetCurrentProject;
