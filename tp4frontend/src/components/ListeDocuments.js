import React, { useState, useEffect } from 'react';
import { fetchDocuments, loanDocument } from '../services/LibraryServices';

function ListeDocuments({ selectedUser }) {
    const [documents, setDocuments] = useState([]);
    const [selectedDocument, setSelectedDocument] = useState(null);
    const [loanData, setLoanData] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const documentsData = await fetchDocuments();
                setDocuments(documentsData);
            } catch (error) {
                console.error('Error fetching documents:', error);
            }
        };
        fetchData().then(r => console.log(r));
    }, []);

    const handleDocumentClick = (document) => {
        setSelectedDocument(document);
    };

    const handleLoanDocument = async () => {
        if (!selectedUser || !selectedDocument) {
            console.error('Please select a user and a document');
            return;
        }

        try {
            const loanData = await loanDocument(selectedUser, selectedDocument);
            setLoanData(loanData);
            const documentsData = await fetchDocuments();
            setDocuments(documentsData);
        } catch (error) {
            console.error('Error loaning document:', error);
        }
    };

    return (
        <div>
            <h2>Liste Des Documents</h2>
            <ul style={{ listStyleType: 'none' }}>
                {documents.map(document => (
                    <li
                        key={document.id}
                        onClick={() => handleDocumentClick(document)}
                        style={{
                            cursor: 'pointer',
                            border: '5px solid #ccc',
                            padding: '10px',
                            marginBottom: '10px'
                        }}
                    >
                        <p>Titre: {document.documentTitle}</p>
                        <p>Nb copies disponibles: {document.nbAvailableCopies}</p>
                            <button onClick={handleLoanDocument}>Loan Document</button>

                        {loanData && (
                            <div>
                                <h2>Loan Data:</h2>
                                <pre>{JSON.stringify(loanData, null, 2)}</pre>
                            </div>
                        )}
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default ListeDocuments;
