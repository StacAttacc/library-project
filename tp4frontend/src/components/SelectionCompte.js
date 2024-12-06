import React, { useState, useEffect } from 'react';
import { fetchLibrarians, fetchBorrowers } from '../services/LibraryServices';

function SelectionCompte({ onUserSelect, borrowers}) {
    const [librarians, setLibrarians] = React.useState([]);
    const [selectedUser, setSelectedUser] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const librariansData = await fetchLibrarians();
                setLibrarians(librariansData);
            } catch (error) {
                console.error('Error fetching users:', error);
            }
        };
        fetchData().then(r => console.log(r));
    }, []);

    const handleUserClick = (user) => {
        setSelectedUser(user);
        onUserSelect(user);
    };

    return (
        <div>
            <h2>Selection Compte</h2>
            <h4>Empreunteurs:</h4>
            <ul style={{ listStyleType: 'none' }}>
                {borrowers.map((borrower) => (
                    <li
                        key={borrower.id}
                        onClick={() => handleUserClick(borrower)}
                        style={{cursor: 'pointer'}}
                    >
                        {borrower.firstName} {borrower.lastName}
                    </li>
                ))}
            </ul>
            <h4>Employes:</h4>
            <ul style={{ listStyleType: 'none' }}>
                {librarians.map((librarian) => (
                    <li
                        key={librarian.id}
                        onClick={() => handleUserClick(librarian)}
                        style={{cursor: 'pointer'}}
                    >
                        {librarian.firstName} {librarian.lastName}
                    </li>
                ))}
            </ul>
            <h4>Utilisateur Courrant:</h4>
            {selectedUser && (
                <div>
                    <p>
                        {selectedUser.firstName} {selectedUser.lastName}
                    </p>
                </div>
            )}
        </div>
    );
}

export default SelectionCompte;
