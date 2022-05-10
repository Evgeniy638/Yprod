import React, { ChangeEventHandler, FC, useCallback, useState } from 'react';
import { Button, TextField } from '@mui/material';
import AddIcon from '@mui/icons-material/Add';
import CloseIcon from '@mui/icons-material/Close';

import './index.css';
import { useBoolean } from '../../hooks/useBoolean';

interface CreateColumnTaskProps {
    dashboardId: number;
}

export const CreateColumnTask: FC<CreateColumnTaskProps> = ({dashboardId}) => {
    const [isActiveForm, openForm, closeForm] = useBoolean();
    const [newTitleColumn, setNewTitleColumn] = useState<string | undefined>();

    const handleChange: ChangeEventHandler<HTMLInputElement> = useCallback(
        ({ target }) => setNewTitleColumn(target.value), 
        []
    );

    const handleAdd = useCallback(() => {
        console.log(dashboardId, newTitleColumn);
    }, [dashboardId, newTitleColumn]);

    if (isActiveForm) {
        return (
            <div className="CreateColumnTask">
                <TextField 
                    value={newTitleColumn}
                    onChange={handleChange}
                    placeholder="Ввести заголовок списка"
                    variant="outlined"
                    size="small"
                    className="CreateColumnTask__Input"
                />
                <div>
                    <Button variant="contained" onClick={handleAdd}>Добавить</Button>
                    <Button 
                        color="inherit" 
                        onClick={closeForm}
                        size="small"
                    >
                        <CloseIcon fontSize="small" />
                    </Button>
                </div>
            </div>
        );
    }

    return (
        <Button className="CreateColumnTaskButton" variant="contained" onClick={openForm}>
            <AddIcon fontSize="small" className="CreateColumnTask__AddIcon" />
            Добавить ещё одну колонку
        </Button>
    );
};
