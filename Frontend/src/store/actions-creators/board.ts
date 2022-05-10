import { ActionBoard, Board, ListTypeBoardActions } from './../types/typeDashboard';

export const setBoard = (board: Board): ActionBoard => ({
    type: ListTypeBoardActions.SET_CURRENT_BOARD,
    board,
});
