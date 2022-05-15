import React, { FC } from 'react';
import { DashboardLayout } from '../../organisms/DashboardLayout';
import { Page } from '../../organisms/Page';
import Profile from '../../organisms/Profile';

const ProfilePage: FC = () => {
    return (
        <Page>
            <DashboardLayout>
                <Profile />
            </DashboardLayout>
        </Page>
    );
};

export default ProfilePage;
