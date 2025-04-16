package clean.architecture.cleanarchitecture.domain.repository;

import java.util.Optional;

import clean.architecture.cleanarchitecture.domain.model.BockModel;

public interface BookRepository {
    
    public void addBook(BockModel book);

    public Optional<BockModel> getBook();
}
