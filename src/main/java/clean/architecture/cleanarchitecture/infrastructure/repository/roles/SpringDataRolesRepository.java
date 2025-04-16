package clean.architecture.cleanarchitecture.infrastructure.repository.roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import clean.architecture.cleanarchitecture.infrastructure.entity.RolesEntity;

@Repository
public interface SpringDataRolesRepository extends JpaRepository<RolesEntity, Integer>{
    
}
