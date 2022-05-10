import { RootState } from './../reducers/index';

export const getCurrentBoard = (state: RootState) => state.reducerBoard.board;
