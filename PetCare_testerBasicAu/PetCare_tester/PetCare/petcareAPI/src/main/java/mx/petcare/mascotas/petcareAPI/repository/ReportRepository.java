package mx.petcare.mascotas.petcareAPI.repository;
import mx.petcare.mascotas.petcareAPI.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}
