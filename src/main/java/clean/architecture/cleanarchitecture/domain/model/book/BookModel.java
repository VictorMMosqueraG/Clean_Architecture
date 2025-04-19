package clean.architecture.cleanarchitecture.domain.model.book;

import java.util.Optional;

import clean.architecture.cleanarchitecture.application.dto.book.UpdateBookDto;

public class BookModel {
    
    private int id;
    private String tittle;
    private String description;

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    
    /**
     * Method to valid files if null or not null.
     * if not null it will update file,
     * if is null it don't update file
     * 
     * @param UpdateBookDto dto 
     * 
     * @return void
    */
    public void updateFields(UpdateBookDto dto) {
        Optional.ofNullable(dto.getTittle()).ifPresent(this::setTittle);
        Optional.ofNullable(dto.getDescription()).ifPresent(this::setDescription);
    }
}
