package clean.architecture.cleanarchitecture.domain.repository.base;

import java.util.List;

public interface BaseRepository<T, ID> {
    
    void save(T entity);
    T findByIdOrFail(ID id);
    void delete(ID id);
    T update(ID id, T entity);
    List<T> findAll();
}
