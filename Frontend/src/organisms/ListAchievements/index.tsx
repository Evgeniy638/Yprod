import { Typography } from '@mui/material';
import classNames from 'classnames';
import React, { FC } from 'react';
import { Link } from 'react-router-dom';
import { createPathToProfileAchievement, parametrs } from '../../common/path';
import { useQuery } from '../../hooks/useQuery';
import { Achievement } from '../../store/types/typeUser';
import AchievementModal from '../AchievementModal';

import './index.css';

interface ListAchievementsProps {
    achievements: Achievement[];
}

interface ItemListAchievementsProps {
    id: number;
    name: string;
    points: number;
    picture: string;
}

const ItemListAchievements: FC<ItemListAchievementsProps> = ({
    id,
    name,
    points,
    picture
}) => {
    return (
        <Link
            to={createPathToProfileAchievement(id)}
            className={classNames('ItemListAchievements', 'ignoreLinkStyle')}
        >
            <img className="ItemListAchievements__img" src={picture} alt={name} />
            <div className="ItemListAchievements__text">
                <span className="ItemListAchievements__name">
                    {name}
                </span>
                <Typography variant="overline" component="span" className="ItemListAchievements__points">
                    +{points}
                </Typography>
            </div>
        </Link>
    );
};

const ListAchievements: FC<ListAchievementsProps> = ({ achievements }) => {
    const params = useQuery();
    const achievementId: string | null = params.get(parametrs.ACHIEVEMENT_ID);

    const achievementPopap: Achievement | undefined = achievementId && achievements.find(ach => ach.id === Number(achievementId));

    return (
        <>
            <div className="ListAchievements">
                {achievements.map(achievement => (
                    <ItemListAchievements key={achievement.id} {...achievement} />
                ))}
            </div>
            {achievementPopap && <AchievementModal {...achievementPopap} /> }
        </>
    );
};

export default ListAchievements;
