import { AppDispatch } from '..';
import { api } from '../../api';
import actionsCreators from '../actions-creators';

export const setCurrentTask = (taskId: number) => 
    async (dispatch: AppDispatch) => {
        const task = await api.getTask({ taskId });

        dispatch(actionsCreators.setTask(task));

        const statuses = await api.getTaskStatuses({ boardId: task.boardId });

        dispatch(actionsCreators.setTaskStatuses(statuses));
    };
