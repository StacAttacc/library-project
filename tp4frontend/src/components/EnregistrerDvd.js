import React, { useState } from 'react';
import {postCd, postDvd} from "../services/LibraryServices";

const EnregistrerDvd = () => {
    const [title, setTitle] = useState('');
    const [author, setAuthor] = useState('');
    const [editor, setEditor] = useState('');
    const [yearOfPublishing, setYearOfPublishing] = useState('');
    const [nbMinutes, setNbMinutes] = useState('');
    const [genre, setGenre] = useState('');
    const [nbCopies, setNbCopies] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        const newDvd = {
            title,
            author,
            editor,
            yearOfPublishing: parseInt(yearOfPublishing),
            nbMinutes: parseInt(nbMinutes),
            genre,
            nbCopies: parseInt(nbCopies),
        };

        await postDvd(newDvd);
    };

    return (
        <div>
            <h2>Enregistrer DVD</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder="Titre"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                />
                <input
                    type="text"
                    placeholder="Auteur"
                    value={author}
                    onChange={(e) => setAuthor(e.target.value)}
                />
                <input
                    type="text"
                    placeholder="Editeur"
                    value={editor}
                    onChange={(e) => setEditor(e.target.value)}
                />
                <input
                    type="number"
                    placeholder="Annee De Publication"
                    value={yearOfPublishing}
                    onChange={(e) => setYearOfPublishing(e.target.value)}
                />
                <input
                    type="number"
                    placeholder="Nombre De Minutes"
                    value={nbMinutes}
                    onChange={(e) => setNbMinutes(e.target.value)}
                />
                <input
                    type="text"
                    placeholder="Genre"
                    value={genre}
                    onChange={(e) => setGenre(e.target.value)}
                />
                <input
                    type="number"
                    placeholder="Nombre of Copies"
                    value={nbCopies}
                    onChange={(e) => setNbCopies(e.target.value)}
                />
                <button type="submit">Enregistrer DVD</button>
            </form>
        </div>
    );
};

export default EnregistrerDvd;
