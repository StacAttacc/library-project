import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/library/v1';

export const fetchDocuments = async () => {
    try {
        const response = await axios.get(`${API_BASE_URL}/document-copies`);
        return response.data;
    } catch (error) {
        console.error('Error fetching books:', error);
        throw error;
    }
};

export const fetchBorrowers = async () => {
    try {
        const response = await axios.get(`${API_BASE_URL}/borrowers`);
        return response.data;
    } catch (error) {
        console.error('Error fetching borrowers:', error);
        throw error;
    }
};

export const fetchLibrarians = async () => {
    try {
        const response = await axios.get(`${API_BASE_URL}/librarians`);
        return response.data;
    } catch (error) {
        console.error('Error fetching librarians:', error);
        throw error;
    }
};

export const postBook = async (newBook) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/book`, newBook);
        console.log(response.data);
    } catch (error) {
        console.error(error);
    }
}

export const postCd = async (newCd) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/cd`, newCd);
        console.log(response.data);
    } catch (error) {
        console.error(error);
    }
}

export const postDvd = async (newDvd) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/dvd`, newDvd);
        console.log(response.data);
    } catch (error) {
        console.error(error);
    }
}

export const loanDocument = async (selectedUser, selectedDocument) => {
    try {
        const newDocumentLoanDTO = {
            borrowerId: selectedUser.id,
            documentCopyStockId: selectedDocument.id,
        };

        const response = await axios.post(`${API_BASE_URL}/loan`, newDocumentLoanDTO);
        return response.data;
    } catch (error) {
        console.error('Error loaning document:', error);
        throw error;
    }
};

export const getBorrowerLoans = async (borrower) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/list-borrower-loans`, borrower);
        return response.data;
    } catch (error) {
        console.error('Error fetching borrower loans:', error);
        throw error;
    }
};

export const postBorrower = async (newBorrower) => {
    try {
        const response = await axios.post(`${API_BASE_URL}/borrower`, newBorrower);
        console.log(response.data);
    } catch (error) {
        console.error(error);
    }
}

export const returnDocumentLoan = async (loan) => {
    try{
        const response = await axios.post(`${API_BASE_URL}/return-loan`, loan);
        console.log(response.data);
    } catch (error) {
        console.error(error);
    }
}