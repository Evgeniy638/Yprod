export const PAGE_TASK = '/task/:taskId';
export const PAGE_TASK_CREATE = '/task/create';
export const PAGE_PROFILE = '/profile';
export const PAGE_DASHBOARD = '/dashboard';
export const PAGE_DASHBOARD_ID = '/dashboard/:dashboardId';

export const createPathToDashboard = (dashboardId: number) =>
    `/dashboard/${dashboardId}`;

export const createPathToCreateTask = (dashboardId: number) =>
    `/task/create?dashboardId=${dashboardId}`;

export const createPathToTask = (taskId: number) =>
    `/task/${taskId}`;

export const parametrs = {
    DASHBOARD_ID: 'dashboardId',
};
