package mx.petcare.mascotas.petcareAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mx.petcare.mascotas.petcareAPI.model.Pet;
import mx.petcare.mascotas.petcareAPI.service.PetService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("pets")
@Tag(name = "Pets", description = "User pets")
@CrossOrigin(origins = "*")
public class PetController {

    @Autowired
    private PetService service;

    @Operation(summary = "Get all pets paginated")
    @ApiResponse(responseCode = "200", description = "Pets retrieved", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Pet.class))) })
    @GetMapping
    public ResponseEntity<Page<Pet>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Pet> pets = service.getPetsPaginated(page, size);
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @Operation(summary = "Get pet by ID")
    @ApiResponse(responseCode = "200", description = "Pet retrieved", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pet.class)))
    @ApiResponse(responseCode = "404", description = "Pet not found")
    @GetMapping("{idPet}")
    public ResponseEntity<Pet> getByIdPet(@PathVariable Integer idPet) {
        Pet pet = service.getByidPet(idPet);
        if (pet == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @Operation(summary = "Create a new pet")
    @ApiResponse(responseCode = "201", description = "Pet created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pet.class)))
    @PostMapping
    public ResponseEntity<Pet> create(@RequestBody Pet pet) {
        if (pet == null || pet.getPetName() == null || pet.getRace() == null || pet.getSpecies() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Pet createdPet = service.save(pet);
        return new ResponseEntity<>(createdPet, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a pet")
    @ApiResponse(responseCode = "200", description = "Pet updated", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pet.class)))
    @ApiResponse(responseCode = "404", description = "Pet not found")
    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Integer id, @RequestBody Pet petDetails) {
        if (petDetails == null || petDetails.getPetName() == null || petDetails.getRace() == null || petDetails.getSpecies() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Pet pet = service.findById(id);
        if (pet == null) {
            return ResponseEntity.notFound().build();
        }
        
        pet.setPetName(petDetails.getPetName());
        pet.setRace(petDetails.getRace());
        pet.setSpecies(petDetails.getSpecies());
        pet.setPetImage(petDetails.getPetImage());

        service.save(pet); 
        return ResponseEntity.ok(pet);
    }

    @Operation(summary = "Delete a pet")
    @ApiResponse(responseCode = "200", description = "Pet deleted")
    @ApiResponse(responseCode = "404", description = "Pet not found")
    @DeleteMapping("{idPet}")
    public ResponseEntity<String> delete(@PathVariable Integer idPet) {
        Pet pet = service.findById(idPet);
        if (pet == null) {
            return new ResponseEntity<>("Pet not found", HttpStatus.NOT_FOUND);
        }

        service.delete(idPet);
        return new ResponseEntity<>("Pet deleted successfully", HttpStatus.OK);
    }
}
