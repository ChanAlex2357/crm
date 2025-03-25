package site.easy.to.build.crm.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.settings.ExpenseSettings;

@Repository
public interface ExpenseSettingsRepository extends JpaRepository<ExpenseSettings, Integer> {
    ExpenseSettings findFirstByOrderByDateTauxDesc();
}
