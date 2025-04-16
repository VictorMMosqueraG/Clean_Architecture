package clean.architecture.cleanarchitecture.infrastructure.mapper.base;

public interface GenericMapper<E, M, D> {

    // E => Entity
    // M => Model
    // D => DTO
    E modelToEntity(M model);
    M entityToModel(E entity);
    M dtoToModel(D dto);
}
