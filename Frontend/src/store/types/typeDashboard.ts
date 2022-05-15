export interface Status {
    id: number;
    name: string;
    order: number;
}

export interface BoardTask {
    id: number;
    name: string;
    statusId: number;
    storyPoints: number;
    executor: {
        name: string;
        picture?: string;
    };
}

export interface Board {
    id: number,
    name: string,
    description: string,
    statuses: Status[],
    tasks: BoardTask[];
}

export interface StateBoard {
    board?: Board;
}

export enum ListTypeBoardActions {
    SET_CURRENT_BOARD = 'SET_CURRENT_BOARD',
    ADD_STATUS = 'ADD_STATUS',
}

interface SetCurrentBoard {
    type: ListTypeBoardActions.SET_CURRENT_BOARD;
    board: Board;
}

interface AddStatus {
    type: ListTypeBoardActions.ADD_STATUS;
    status: Status;
}

export type ActionBoard = SetCurrentBoard | AddStatus;
