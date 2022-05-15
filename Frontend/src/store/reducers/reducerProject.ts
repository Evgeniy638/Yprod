import { StateProject, ListTypeProjectActions, ActionProject } from './../types/typeProject';

const initialState: StateProject = {};

export const reducerProject = (
    state: StateProject = initialState,
    action: ActionProject,
): StateProject => {
    switch (action.type) {
        case ListTypeProjectActions.SET_CURRENT_PROJECT:
            return {
                ...state,
                project: action.project,
            };
        
        case ListTypeProjectActions.ADD_ACHIEVEMENT_TO_PROJECT:
            return {
                ...state,
                project: {
                    ...state.project,
                    achievements: [
                        ...state.project.achievements,
                        action.achievement,
                    ],
                },
            };

        default:
            return state;
    }
};
