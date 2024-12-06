import React, {useEffect, useState} from 'react';
import Sidebar from './components/Sidebar';
import MainContent from './components/MainContent';
import './App.css';
import SelectionCompte from "./components/SelectionCompte";
import ListeDocuments from './components/ListeDocuments';
import {fetchBorrowers, fetchLibrarians} from "./services/LibraryServices";

function App() {
    const [selectedItem, setSelectedItem] = useState('ListeDocuments');
    const [selectedUser, setSelectedUser] = useState(null);
    const [borrowers, setBorrowers] = React.useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const borrowersData = await fetchBorrowers();
                setBorrowers(borrowersData);
            } catch (error) {
                console.error('Error fetching users:', error);
            }
        };
        fetchData().then(r => console.log(r));
    }, []);

    const handleUserSelect = (user) => {
        setSelectedUser(user);
    };

    const handleSidebarItemClick = (item) => {
        setSelectedItem(item);
    };

    return (
        <div className="App">
            <div className="sidebar">
                <Sidebar onItemClick={handleSidebarItemClick} />
            </div>
            <div className="content">
                {selectedItem === 'ListeDocuments' ? (
                    <ListeDocuments selectedUser={selectedUser} />
                ) : (
                    <MainContent
                        selectedItem={selectedItem}
                        selectedUser={selectedUser}
                    />
                )}
            </div>
            <div className="sidebar">
                <SelectionCompte onUserSelect={handleUserSelect}
                                 borrowers={borrowers}/>
            </div>
        </div>
    );
}

export default App;
