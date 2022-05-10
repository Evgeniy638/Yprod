import React from 'react';
import { Redirect, Route, Switch } from 'react-router-dom';

import './App.css';
import { DashboardPage } from './pages/_dashboard';
import { DashboardIdPage } from './pages/_dashboard/@id';

const App: React.FC = () => {
    return (
        <div className="App">
            <Switch>
                <Route path="/dashboard/:dashboardId">
                    <DashboardIdPage />
                </Route>

                <Route path="/dashboard">
                    <DashboardPage />
                </Route>

                <Redirect to="/dashboard" />
            </Switch>
        </div>
    );
};

export default App;
