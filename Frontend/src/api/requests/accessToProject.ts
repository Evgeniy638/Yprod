import { AxiosResponse } from 'axios';
import { get } from 'lodash';
import { ERROR_NOT_FOUND_USER } from '../../common/errors';
import { instance } from './../instance';

export const accessToProject = async () => {
    try {
        await instance.post<null>('/api/project/access');
    } catch (error) {
        const response = get(error, 'response') as AxiosResponse | undefined;

        if (response.status === 404) {
            throw ERROR_NOT_FOUND_USER;
        }
    }
};
