import { Typography } from '@mui/material';
import React, { FC } from 'react';
import { useHistory, useLocation } from 'react-router-dom';
import Modal from '../../molecules/Modal';

import './index.css';

interface AchievementModalProps {
    id: number;
    name: string;
    description: string;
    points: number;
    picture: string;
}

const AchievementModal: FC<AchievementModalProps> = ({
    name,
    description,
    points,
    picture,
}) => {
    const history = useHistory();
    const location = useLocation();

    const handleClose = () => {
        history.replace(location.pathname);
    };

    return (
        <Modal isOpen onClose={handleClose}>
            <p className="AchievementModal">
                <img src={picture} className="AchievementModal__img" alt={name} />
                <Typography variant="h6" component="h6" className="AchievementModal__name" gutterBottom>
                    {name}
                </Typography>
                <Typography variant="overline" component="p" gutterBottom>
                    Опыт: {points}
                </Typography>
                <Typography variant="body1" component="p" gutterBottom>
                    {description}
                </Typography>
            </p>
        </Modal>
    );
};

export default AchievementModal;
