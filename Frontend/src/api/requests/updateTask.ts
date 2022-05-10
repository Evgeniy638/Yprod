import { instance } from './../instance';
import { CurrentTask } from './../../store/types/typeTask';

interface Args {
    taskId: number;
    data: {
        name: string;
        description: string;
        storyPoints: number;
        executorEmail: string;
        statusId: number;
    }
}

type Response = CurrentTask;

export const updateTask = async ({ taskId, data }: Args) => {
    const response = await instance.put<Response>(`/api/task/${taskId}`, data);

    return response.data;
};
