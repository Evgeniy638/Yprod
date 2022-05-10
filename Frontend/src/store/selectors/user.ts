import { RootState } from '../reducers';

export const selectUser = (state: RootState) => state.reducerUser.user;

export const selectPrimaryBoards = (state: RootState) => state.reducerUser.boards;
