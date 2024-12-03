package mx.petcare.mascotas.petcareAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.petcare.mascotas.petcareAPI.model.ShareTip;

@Repository
public interface ShareTipRepository extends JpaRepository<ShareTip, Integer> {

    
}
