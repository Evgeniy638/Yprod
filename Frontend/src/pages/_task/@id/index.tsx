import React, { FC } from 'react';
import { useParams } from 'react-router-dom';
import { DashboardLayout } from '../../../organisms/DashboardLayout';
import { Page } from '../../../organisms/Page';

const TaskIdPage: FC = () => {
    const {taskId} = useParams<{taskId: string}>();

    return (
        <Page>
            <DashboardLayout>
                taskId {taskId}
            </DashboardLayout>
        </Page>
    );
};

export default TaskIdPage;
