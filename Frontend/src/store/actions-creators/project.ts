import { ActionProject, ListTypeProjectActions, Project } from './../types/typeProject';


export const setCurrentProject = (project: Project): ActionProject => ({
    type: ListTypeProjectActions.SET_CURRENT_PROJECT,
    project,
});
