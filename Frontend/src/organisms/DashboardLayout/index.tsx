import React, { FC, ReactNode } from 'react';
import { List, ListItem, ListItemButton, ListItemText, Typography } from '@mui/material';
import AddIcon from '@mui/icons-material/Add';

import './index.css';
import { Link, useParams } from 'react-router-dom';
import classNames from 'classnames';

interface ItemListBoard {
    text: string; 
    id: string;
}

const listBoard: ItemListBoard[] = [...(new Array<undefined>(30))].map((_val, index) => ({
    text: `Доска ${index}`,
    id: String(index),
}));

interface DashboardLayoutProps {
    classNameMain?: string;
    children?: ReactNode;
}

export const DashboardLayout: FC<DashboardLayoutProps> = ({children, classNameMain}) => {
    const { dashboardId } = useParams<{dashboardId: string}>();

    return (
        <div className="DashboardLayout">
            <div className="DashboardLayout__left">
                <Typography component="h3" variant="h6" noWrap>
                    Название организации
                </Typography>

                <div>
                    <Typography component="h4" variant="h6" noWrap className="DashboardLeft__subtitleWrap">
                        <span className="DashboardLeft__subtitle">
                            Доски
                        </span>
                        <AddIcon fontSize="small" className="DashboardLeft__action" />
                    </Typography>

                    <List className="DashboardLeft__list">
                        {listBoard.map(({id, text}) => (
                            <Link to={`/dashboard/${id}`} className="DashboardLayout__link" key={id}>
                                <ListItem disablePadding className={classNames({
                                    DashboardLayout__licstItem_active: dashboardId === id,
                                })}>
                                    <ListItemButton>
                                        <ListItemText primary={text} />
                                    </ListItemButton>
                                </ListItem>
                            </Link>
                        ))}
                    </List>
                </div>
            </div>
            <div className={classNames('DashboardLayout__main', classNameMain)}>
                {children}
            </div>
        </div>
    );
};
