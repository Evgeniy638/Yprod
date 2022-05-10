import React, { FC } from 'react';
import { Header } from '../Header';

import './index.css';

export const Page: FC = ({children}) => {
    return (
        <div className="Page">
            <Header />
            <main className="Page__main">
                {children}
            </main>
        </div>
    );
};
