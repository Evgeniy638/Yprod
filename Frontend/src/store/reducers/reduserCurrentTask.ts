import { StateTask, ActionCurrentTask, ListTypeTaskActions } from './../types/typeTask';

const initialState: StateTask = {
    taskStatuses: [],
};

export const reduserCurrentTask = (
    state: StateTask = initialState,
    action: ActionCurrentTask,
): StateTask => {
    switch (action.type) {
        case ListTypeTaskActions.SET_CURRENT_TASK:
            return {
                ...state,
                task: action.task,
            };
        case ListTypeTaskActions.SET_TASK_STATUSES:
            return {
                ...state,
                taskStatuses: action.taskStatuses,
            };

        default:
            return state;
    }
};
