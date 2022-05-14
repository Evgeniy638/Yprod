import React, { FC, ReactNode } from 'react';
import { Header } from '../Header';

import './index.css';

interface PageProps {
    children?: ReactNode;
}

export const Page: FC<PageProps> = ({children}) => {
    return (
        <div className="Page">
            <Header />
            <main className="Page__main">
                {children}
            </main>
        </div>
    );
};
