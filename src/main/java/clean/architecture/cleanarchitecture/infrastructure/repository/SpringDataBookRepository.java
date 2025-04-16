package clean.architecture.cleanarchitecture.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import clean.architecture.cleanarchitecture.infrastructure.entity.BookEntity;

@Repository
public interface SpringDataBookRepository extends JpaRepository<BookEntity, Integer>{

    
} 
