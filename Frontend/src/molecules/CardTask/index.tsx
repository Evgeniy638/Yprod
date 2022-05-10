import { Chip, Typography } from '@mui/material';
import React, { FC } from 'react';
import { Link } from 'react-router-dom';

import './index.css';

export interface CardTaskProps {
    taskId: string;
    title: string;
    tags: string[];
}

export const CardTask: FC<CardTaskProps> = ({
    taskId,
    title,
    tags,
}) => {
    return (
        <div className="CardTask">
            <Link className="ignoreLinkStyle" to={`/task/${taskId}`}>
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
