import React, {useEffect, useState} from 'react';
import {getBorrowerLoans, returnDocumentLoan} from "../services/LibraryServices";

const ListeEmpreunts = ({ selectedUser }) => {
    const [userLoans, setUserLoans] = useState([]);

    useEffect(() => {
        const fetchBorrowerLoans = async () => {
            if (selectedUser) {
                try {
                    const loans = await getBorrowerLoans(selectedUser);
                    if (loans && loans.length > 0) {
                        setUserLoans(loans);
                        console.log(loans);
                    } else {
                        setUserLoans([]);
                    }
                } catch (error) {
                    console.error('Error fetching loans:', error);
                    setUserLoans([]);
                }
            }
        };

        fetchBorrowerLoans().then(r => console.log(r));
    }, [selectedUser]);

    const handleReturnLoan = async (loan) => {
        try {
            await returnDocumentLoan(loan);
            const updatedLoans = await getBorrowerLoans(selectedUser);
            setUserLoans(updatedLoans);
        } catch (error) {
            console.error('Error returning loan:', error);
        }
    };

    return (
        <div>
            <h2>Liste des empreunts</h2>
            {selectedUser ? (
                <div>
                    <p>{selectedUser.firstName} {selectedUser.lastName}</p>
                    <ul style={{ listStyleType: 'none' }}>
                        {userLoans.map((loan) => (
                            <li key={loan.id}
                                style={{
                                    border: '5px solid #ccc',
                                    padding: '10px',
                                    marginBottom: '10px',
                                }}
                            >
                                <p>Titre: {loan.documentTitle}</p>
                                <p>Date de retour prevu: {loan.expectedReturnDate}</p>
                                <p>
                                    Est retourné:{' '}
                                    {loan.returned ? (
                                        <span style={{color: 'green'}}>Oui</span>
                                    ) : (
                                        <span style={{color: 'red'}}>Non</span>
                                    )}
                                </p>
                                <p>
                                    Amendes:{' '}
                                    {loan.fine ? loan.fine : 'Pas d\'amendes'}
                                </p>
                                {!loan.returned && (
                                    <button onClick={() => handleReturnLoan(loan)}>
                                        Retourner
                                    </button>
                                )}
                            </li>
                        ))}
                    </ul>
                </div>
            ) : (
                <p>Selectionnez un empreunteur pour voir leur liste d'empreunts</p>
            )}
        </div>
    );
};

export default ListeEmpreunts;
