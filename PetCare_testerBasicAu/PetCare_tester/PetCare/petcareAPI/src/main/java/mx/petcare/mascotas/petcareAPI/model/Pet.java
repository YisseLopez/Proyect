package mx.petcare.mascotas.petcareAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Column;

@Entity
@Table(name = "Pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PetId") 
    private int petId;



    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)  
    private User user;


    @NotBlank(message = "Content the information of the pet")
    @Column(name = "PetName", nullable = false)
    @Size(min = 1, max = 50, message = "PetName must be between 1 and 50 characters")
    private String petName;

    @NotBlank(message = "Content the information of the race about the pet")
    @Column(name = "Race", nullable = false)
    @Size(min = 1, max = 100, message = "Race must be between 1 and 100 characters")
    private String race;

    @NotBlank(message = "Species is mandatory")
    @Column(name = "Species", nullable = false)
    @Size(min = 1, max = 100, message = "Species must be between 1 and 100 characters")
    private String species;

    private String petImage;

    public Pet() {
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getPetImage() {
        return petImage;
    }

    public void setPetImage(String petImage) {
        this.petImage = petImage;
    }

    @Override
    public String toString() {
        return "Pet [petId=" + petId + ", user=" + user + ", petName=" + petName + ", race=" + race + ", species="
                + species + ", petImage=" + petImage + "]";
    }

   
    public Pet(User user, String petName, String race, String species, String petImage) {
        this.user = user;
        this.petName = petName;
        this.race = race;
        this.species = species;
        this.petImage = petImage;
    }

    
    public Pet(Integer petId, User user, String petName, String race, String species, String petImage) {
        this.petId = petId;
        this.user = user;
        this.petName = petName;
        this.race = race;
        this.species = species;
        this.petImage = petImage;
    }

    public Pet(User user, String petName, String race, String species) {
        this.user = user;
        this.petName = petName;
        this.race = race;
        this.species = species;
        this.petImage = null; 
    }

    
}
