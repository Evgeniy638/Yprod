import React, { FC } from 'react';
import { useSelector } from 'react-redux';
import { CardTask } from '../../molecules/CardTask';
import { ColumnTasks } from '../../molecules/ColumnTasks';
import { CreateColumnTask } from '../../molecules/CreateColumnTask';
import { selectors } from '../../store';

import './index.css';

interface DashboardProps {
    dashboardId: number;
}

export const Dashboard: FC<DashboardProps> = ({dashboardId}) => {
    const { tasks, statuses } = useSelector(selectors.getCurrentBoard);
    
    return (
        <div className="Dashboard">
            {statuses.sort((s1, s2) => s1.order - s2.order).map(status => (
                <div key={status.id} className="Dashboard__column">
                    <ColumnTasks title={status.name} dashboardId={dashboardId}>
                        {tasks
                            .filter(task => task.statusId === status.id)
                            .map(task => (
                            <CardTask 
                                key={task.id} 
                                taskId={task.id}
                                title={task.name}
                            />
                        ))}
                    </ColumnTasks>
                </div>
            ))}
            <div className="Dashboard__column">
                <CreateColumnTask order={statuses.length} dashboardId={dashboardId} />
            </div>
        </div>
    );
};
