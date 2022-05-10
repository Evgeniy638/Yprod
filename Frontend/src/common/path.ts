export const PAGE_TASK = '/task';
export const PAGE_PROFILE = '/profile';
export const PAGE_DASHBOARD = '/dashboard';
export const PAGE_DASHBOARD_ID = '/dashboard/:dashboardId';

export const createPathToCreateTask = (dashboardId: string) =>
    `/task/create?dashboardId=${dashboardId}`;

export const createPathToTask = (taskId: string) =>
    `/task/${taskId}`;
