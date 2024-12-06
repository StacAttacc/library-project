import React, { useState } from 'react';
import {fetchBorrowers, postBorrower} from "../services/LibraryServices";

const EnregistrerEmpreunteur = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        const newBorrower = {
            firstName,
            lastName,
        };

        await postBorrower(newBorrower);
    };

    return (
        <div>
            <h2>Enregistrer Empreunteur</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder="Prenom"
                    value={firstName}
                    onChange={(e) => setFirstName(e.target.value)}
                />
                <input
                    type="text"
                    placeholder="Nom"
                    value={lastName}
                    onChange={(e) => setLastName(e.target.value)}
                />
                <button type="submit">Enregistrer Empreunteur</button>
            </form>
        </div>
    );
}

export default EnregistrerEmpreunteur;