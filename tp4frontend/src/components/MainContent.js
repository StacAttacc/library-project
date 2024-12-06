import React from 'react';
import ListeDocuments from "./ListeDocuments";
import ListeEmpreunts from "./ListeEmpreunts";
import EnregistrerLivre from "./EnregistrerLivre";
import EnregistrerCd from "./EnregistrerCd";
import EnregistrerDvd from "./EnregistrerDvd";
import EnregistrerEmpreunteur from "./EnregistrerEmpreunteur";

const MainContent = ({ selectedItem, selectedUser}) => {
    const renderContent = () => {
        switch (selectedItem) {
            case 'ListeDocuments':
                return <ListeDocuments />;
            case 'ListeEmpreunts':
                return <ListeEmpreunts selectedUser={selectedUser} />;
            case 'EnregistrerLivre':
                return <EnregistrerLivre />;
            case 'EnregistrerCd':
                return <EnregistrerCd />;
            case 'EnregistrerDvd':
                return <EnregistrerDvd />;
            case 'EnregistrerEmpreunteur':
                return <EnregistrerEmpreunteur />
            default:
                return null;
        }
    };

    return <div>{renderContent()}</div>;
};

export default MainContent;