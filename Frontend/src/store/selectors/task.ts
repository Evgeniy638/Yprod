import { RootState } from './../reducers/index';

export const getCurrentTask = (state: RootState) => state.reduserCurrentTask.task;

export const getTaskStatuses = (state: RootState) => state.reduserCurrentTask.taskStatuses;
