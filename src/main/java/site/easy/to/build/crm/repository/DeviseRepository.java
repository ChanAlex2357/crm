package site.easy.to.build.crm.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import site.easy.to.build.crm.entity.Devise;

public interface DeviseRepository extends JpaRepository<Devise, Integer> {

    
}