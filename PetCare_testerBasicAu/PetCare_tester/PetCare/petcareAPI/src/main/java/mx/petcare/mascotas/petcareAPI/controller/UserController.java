/*package mx.petcare.mascotas.petcareAPI.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.petcare.mascotas.petcareAPI.model.User;
import mx.petcare.mascotas.petcareAPI.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@Tag(name = "User", description = "Operation form users")
public class UserController {

    @Autowired
    private UserService service;

    @Operation(summary = "Get all users with pagination")
    @ApiResponse(responseCode = "200", description = "List of users obtained", content = {
        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))})
    @GetMapping(value = "pagination", params = {"page", "pageSize"})
    public List<User> getAllPaginationQ(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                        @RequestParam(value = "pageSize", defaultValue = "5", required = false)int pageSize) {
        List<User> users = service.getAllPagination(page, pageSize);
        return users;
    }

    @Operation(summary = "Get a specific user by ID")
    @ApiResponse(responseCode = "200", description = "User not found", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))})
    @ApiResponse(responseCode = "404", description = "User not found")
    @GetMapping("/{userId}")
    public ResponseEntity<User> getByUserId(@PathVariable Integer userId) {
        try {
            User user = service.getByUserId(userId);
            return ResponseEntity.ok(user);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Register a new user")
    @ApiResponse(responseCode = "201", description = "User register successfully", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))})
    @PostMapping
    public ResponseEntity<User> register(@RequestBody User user) {
        try {
            User createdUser = service.save(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Update an existing user")
    @ApiResponse(responseCode = "200", description = "User Successfully Updated", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))})
    @ApiResponse(responseCode = "404", description = "User not found")
    @PutMapping("/{userId}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Integer userId) {
        try {
            user.setUserId(userId);
            User updatedUser = service.update(user);
            return ResponseEntity.ok(updatedUser);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found with the ID provided");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error when updating the user: " + e.getMessage());
        }
    }

    @Operation(summary = "Delete a user")
    @ApiResponse(responseCode = "204", description = "User successfully deleted")
    @ApiResponse(responseCode = "404", description = "User not found")
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> delete(@PathVariable Integer userId) {
        try {
            service.delete(userId);
            return ResponseEntity.ok("User successfully deleted");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found with the ID provided");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user");
        }
    }
    @Operation(summary = "Get a specific user by Email")
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email){
        User user = service.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
}
*/