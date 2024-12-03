package mx.petcare.mascotas.petcareAPI.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import mx.petcare.mascotas.petcareAPI.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    @Query("SELECT user FROM User user WHERE user.email = :email")
    Optional<User> findByEmailJPQL(@Param("email") String email);
}
