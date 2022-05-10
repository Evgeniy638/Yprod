import React, { FC } from 'react';
import { CardTask, CardTaskProps } from '../../molecules/CardTask';
import { ColumnTasks } from '../../molecules/ColumnTasks';
import { CreateColumnTask } from '../../molecules/CreateColumnTask';

import './index.css';

interface DashboardProps {
    dashboardId: string;
}

const tasks: CardTaskProps[] = Object.keys([...new Array<unknown>(10)]).map(taskId => ({
    taskId: `YPROD-${taskId}`,
    title: 'Интересный заголовок',
    tags: Number(taskId) % 2 ===0 ? ['тег', 'тег', 'длииииииииный_тег', 'тег'] : [],
}));

export const Dashboard: FC<DashboardProps> = ({dashboardId}) => {
    return (
        <div className="Dashboard">
            <div className="Dashboard__column">
                <ColumnTasks title='Открыто' dashboardId={dashboardId}>
                    {tasks.map(task => (
                        <CardTask 
                            key={task.taskId} 
                            taskId={task.taskId}
                            title={task.title}
                            tags={task.tags}
                        />
                    ))}
                </ColumnTasks>
            </div>
            <div className="Dashboard__column">
                <ColumnTasks title='В работе' dashboardId={dashboardId}>
                    {tasks.slice(0, 4).map(task => (
                        <CardTask 
                            key={task.taskId} 
                            taskId={task.taskId}
                            title={task.title}
                            tags={task.tags}
                        />
                    ))}
                </ColumnTasks>
            </div>
            <div className="Dashboard__column">
                <ColumnTasks title='На ревью' dashboardId={dashboardId}>
                    {tasks.map(task => (
                        <CardTask 
                            key={task.taskId} 
                            taskId={task.taskId}
                            title={task.title}
                            tags={task.tags}
                        />
                    ))}
                </ColumnTasks>
            </div>
            <div className="Dashboard__column">
                <ColumnTasks title='Решено' dashboardId={dashboardId}>
                    {tasks.map(task => (
                        <CardTask 
                            key={task.taskId} 
                            taskId={task.taskId}
                            title={task.title}
                            tags={task.tags}
                        />
                    ))}
                </ColumnTasks>
            </div>
            <div className="Dashboard__column">
                <ColumnTasks title='Закрыто' dashboardId={dashboardId}>
                </ColumnTasks>
            </div>
            <div className="Dashboard__column">
                <CreateColumnTask dashboardId={dashboardId} />
            </div>
        </div>
    );
};
