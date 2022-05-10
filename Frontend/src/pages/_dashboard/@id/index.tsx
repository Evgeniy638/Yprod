import React from 'react';
import { useParams } from 'react-router-dom';
import { Dashboard } from '../../../organisms/Dashboard';
import { DashboardLayout } from '../../../organisms/DashboardLayout';
import { Page } from '../../../organisms/Page';

import './index.css';

export const DashboardIdPage = () => {
    const {dashboardId} = useParams<{dashboardId: string}>();

    return (
        <Page>
            <DashboardLayout classNameMain="DashboardIdPage__main">
                {dashboardId 
                    ? <Dashboard dashboardId={Number(dashboardId)}/>
                    : 'Доска не найдена'
                }
            </DashboardLayout>
        </Page>
    );
};
