package mx.petcare.mascotas.petcareAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import mx.petcare.mascotas.petcareAPI.model.Tip;

@Repository
public interface TipRepository extends JpaRepository<Tip, Integer> {
    
    Page<Tip> findAll(Pageable pageable);
}
