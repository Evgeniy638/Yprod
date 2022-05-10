import { AppDispatch } from '..';
import { api } from '../../api';
import actionsCreators from '../actions-creators';

export const setCurrentProject = (projectId: number) => 
    async (dispatch: AppDispatch) => {
        const project = await api.getProjectInfo({ projectId });

        dispatch(actionsCreators.setCurrentProject(project));
    };
