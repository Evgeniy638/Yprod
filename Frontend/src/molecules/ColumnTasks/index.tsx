import React, { FC } from 'react';
import { Link } from 'react-router-dom';
import AddIcon from '@mui/icons-material/Add';

import './index.css';
import { Button, Typography } from '@mui/material';

interface ColumnTasksProps {
    dashboardId: string;
    title: string;
}

export const ColumnTasks: FC<ColumnTasksProps> = ({dashboardId, title, children}) => {
    return (
        <div className="ColumnTasks">
            <Typography variant="h6" component="h3">
                {title}
            </Typography>
            <div className="ColumnTasks__list">
                {children}
            </div>
            <Link 
                to={`/task/create?dashboardId=${dashboardId}`}
                className="ColumnTasks__link"
            >
                <Button size="small" color="inherit" className="ColumnTasks__linkButton">
                    <AddIcon fontSize="small" className="ColumnTasks__AddIcon" />
                    Создать задачу
                </Button>
            </Link>
        </div>
    );
};
