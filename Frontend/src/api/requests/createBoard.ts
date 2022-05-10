import { instance } from './../instance';
interface CreateBoardArgs {
    projectId: number;
    name: string;
    description: string;
}

interface Response {
    boardId: number;
    name: string;
    description: string;
}

export const createBoard = async (args: CreateBoardArgs) =>
    instance.post<Response>('/api/board', args);
