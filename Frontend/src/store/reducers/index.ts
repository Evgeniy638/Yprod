import { reducerUser } from './reducerUser';
import { combineReducers } from 'redux';
import { reducerBoard } from './reducerBoard';
import { reduserCurrentTask } from './reduserCurrentTask';

export const rootReducer = combineReducers({
    reducerUser,
    reducerBoard,
    reduserCurrentTask
});

export type RootState = ReturnType<typeof rootReducer>
