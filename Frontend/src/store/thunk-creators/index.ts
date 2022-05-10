import * as user from './user';
import * as board from './board';

export const thunkCreators = {
    ...user,
    ...board,
};
