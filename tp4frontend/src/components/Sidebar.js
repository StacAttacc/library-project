import React, { useState } from 'react';

const Sidebar = ({ onItemClick }) => {
    const [activeSection, setActiveSection] = useState('GENERAL1');

    const handleItemClick = (item) => {
        setActiveSection(item);
        onItemClick(item);
    };

    return (
        <div>
            <h1>Bibliotheque</h1>
            <ul style={{ listStyleType: 'none' }}>
                <li onClick={() => handleItemClick('ListeDocuments')}>Liste Documents</li>
                <li onClick={() => handleItemClick('ListeEmpreunts')}>Liste Empreunts</li>
                <li onClick={() => handleItemClick('EnregistrerLivre')}>Enregistrer Livre</li>
                <li onClick={() => handleItemClick('EnregistrerCd')}>Enregistrer Cd</li>
                <li onClick={() => handleItemClick('EnregistrerDvd')}>Enregistrer Dvd</li>
                <li onClick={() => handleItemClick('EnregistrerEmpreunteur')}>Enregistrer Empreunteur</li>
            </ul>
        </div>
    );
};

export default Sidebar;
