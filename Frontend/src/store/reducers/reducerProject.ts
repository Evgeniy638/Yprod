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

        default:
            return state;
    }
};
