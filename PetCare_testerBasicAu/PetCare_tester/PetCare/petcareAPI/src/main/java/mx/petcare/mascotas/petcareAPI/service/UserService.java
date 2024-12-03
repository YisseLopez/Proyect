package mx.petcare.mascotas.petcareAPI.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Optional;
import mx.petcare.mascotas.petcareAPI.model.User;
import mx.petcare.mascotas.petcareAPI.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getAllPagination(int page, int pageSize) {
        PageRequest pageRq = PageRequest.of(page, pageSize);
        Page<User> users = repository.findAll(pageRq);
        return users.getContent();
    }

    public User getByUserId(Integer userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
    }

    public User save(User user) {
        // Verifica si el usuario ya existe
        if (user.getUserId() != 0) {
            User existingUser = repository.findById(user.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + user.getUserId()));
            // Actualiza solo los campos no nulos
            if (user.getFirstName() != null) existingUser.setFirstName(user.getFirstName());
            if (user.getLastName() != null) existingUser.setLastName(user.getLastName());
            if (user.getEmail() != null) existingUser.setEmail(user.getEmail());
            if (user.getPhone() != null) existingUser.setPhone(user.getPhone());
            if (user.getPassword() != null) existingUser.setPassword(user.getPassword());
            return repository.save(existingUser);
        }
        // Si es un nuevo usuario, solo lo guarda
        return repository.save(user);
    }

    public void delete(Integer userId) {
        if (!repository.existsById(userId)) {
            throw new NoSuchElementException("User not found with ID: " + userId);
        }
        repository.deleteById(userId);
    }

    public User update(User user) {
        User existingUser = repository.findById(user.getUserId())
            .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + user.getUserId()));
        
        // Actualiza solo los campos no nulos
        if (user.getFirstName() != null) existingUser.setFirstName(user.getFirstName());
        if (user.getLastName() != null) existingUser.setLastName(user.getLastName());
        if (user.getEmail() != null) existingUser.setEmail(user.getEmail());
        if (user.getPhone() != null) existingUser.setPhone(user.getPhone());
        if (user.getPassword() != null) existingUser.setPassword(user.getPassword());
        
        try {
            return repository.save(existingUser);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el usuario actualizado: " + e.getMessage());
        }
    }

    public User createUser(User user) {
        try {
            // Verifica si el email ya existe
            if (repository.findByEmail(user.getEmail()).isPresent()) {
                throw new RuntimeException("Ya existe un usuario con este email");
            }
            
            // Confirma de que el ID sea nulo para crear un nuevo usuario
            user.setUserId(0);
            
            return repository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el usuario: " + e.getMessage());
        }
    }

    public User getUserByEmail(String email){
        Optional<User> userOptional = repository.findByEmailJPQL(email);
        return userOptional.get();
    }
}
