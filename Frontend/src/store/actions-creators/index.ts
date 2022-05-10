import * as user from './user';
import * as board from './board';
import * as task from './task';

const actionsCreators = {
    ...user,
    ...board,
    ...task,
};

export default actionsCreators;
