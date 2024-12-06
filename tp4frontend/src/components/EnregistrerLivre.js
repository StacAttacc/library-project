import React, { useState } from 'react';
import {postBook} from "../services/LibraryServices";

const EnregistrerLivre = () => {
    const [title, setTitle] = useState('');
    const [author, setAuthor] = useState('');
    const [editor, setEditor] = useState('');
    const [yearOfPublishing, setYearOfPublishing] = useState('');
    const [nbPages, setNbPages] = useState('');
    const [genre, setGenre] = useState('');
    const [nbCopies, setNbCopies] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        const newBook = {
            title,
            author,
            editor,
            yearOfPublishing: parseInt(yearOfPublishing),
            nbPages: parseInt(nbPages),
            genre,
            nbCopies: parseInt(nbCopies),
        };

        await postBook(newBook);
    };

    return (
        <div>
            <h2>Enregistrer Livre</h2>
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
                    placeholder="Nombre de Pages"
                    value={nbPages}
                    onChange={(e) => setNbPages(e.target.value)}
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
                <button type="submit">Enregistrer Livre</button>
            </form>
        </div>
    );
};

export default EnregistrerLivre;
