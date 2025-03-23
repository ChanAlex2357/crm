package site.easy.to.build.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.AlertType;

@Repository
public interface AlertTypeRepository extends JpaRepository<AlertType, Integer> {
    // Custom queries can be added here if needed.
}
