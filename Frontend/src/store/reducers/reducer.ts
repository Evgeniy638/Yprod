const initialState = {
};

interface Action {
    type: string;
}

const reducer = (state=initialState, action: Action) => {
    switch (action.type) {
        default: {
            return state;
        }
    }
};

export default reducer;