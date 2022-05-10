import { Typography } from '@mui/material';
import React, { FC } from 'react';

import './index.css';

interface HeaderProps {
    title?: string;
}

export const Header: FC<HeaderProps> = ({title}) => {
    return (
        <header className='Header'>
            <Typography component="h1" variant="h4" className="Header__title">
                {title || 'Yprod'}
            </Typography>
        </header>
    );
};
