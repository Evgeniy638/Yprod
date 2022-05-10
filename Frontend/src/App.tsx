import React from 'react';
import { Redirect, Route, Switch } from 'react-router-dom';

import './App.css';
import { PAGE_DASHBOARD, PAGE_DASHBOARD_ID } from './common/path';
import { DashboardPage } from './pages/_dashboard';
import { DashboardIdPage } from './pages/_dashboard/@id';

const App: React.FC = () => {
    return (
        <div className="App">
            <Switch>
                <Route path={PAGE_DASHBOARD_ID}>
                    <DashboardIdPage />
                </Route>

                <Route path={PAGE_DASHBOARD}>
                    <DashboardPage />
                </Route>

                <Redirect to={PAGE_DASHBOARD} />
            </Switch>
        </div>
    );
};

export default App;
