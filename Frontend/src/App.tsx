import { SnackbarProvider } from 'notistack';
import React, { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { Redirect, Route, Switch } from 'react-router-dom';

import './App.css';
import { PAGE_DASHBOARD, PAGE_DASHBOARD_ID, PAGE_TASK, PAGE_TASK_CREATE } from './common/path';
import { DashboardPage } from './pages/_dashboard';
import { DashboardIdPage } from './pages/_dashboard/@id';
import TaskIdPage from './pages/_task/@id';
import TaskCreatePage from './pages/_task/_create';
import { thunkCreators } from './store';

const App: React.FC = () => {
    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(thunkCreators.setMainUserInfo());
    }, []);

    return (
        <SnackbarProvider maxSnack={3}>
            <div className="App">
                <Switch>
                    <Route path={PAGE_TASK_CREATE}>
                        <TaskCreatePage />
                    </Route>

                    <Route path={PAGE_TASK}>
                        <TaskIdPage />
                    </Route>

                    <Route path={PAGE_DASHBOARD_ID}>
                        <DashboardIdPage />
                    </Route>

                    <Route path={PAGE_DASHBOARD}>
                        <DashboardPage />
                    </Route>

                    <Redirect to={PAGE_DASHBOARD} />
                </Switch>
            </div>
        </SnackbarProvider>
    );
};

export default App;
