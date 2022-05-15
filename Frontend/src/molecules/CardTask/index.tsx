import { Chip, Typography } from '@mui/material';
import React, { FC } from 'react';
import { Link } from 'react-router-dom';
import { createPathToTask } from '../../common/path';

import './index.css';

export interface CardTaskProps {
    taskId: number;
    title: string;
    tags?: string[];
}

export const CardTask: FC<CardTaskProps> = ({
    taskId,
    title,
    tags=[],
}) => {
    return (
        <div className="CardTask">
            <Link className="ignoreLinkStyle" to={createPathToTask(taskId)}>
                <Typography>
                    {taskId}:{title}
                </Typography>
            </Link>
            {tags.map(textTag => (
                <Chip key={textTag} label={textTag} className="CardTask__Chip" />
            ))}
        </div>
    );
};
