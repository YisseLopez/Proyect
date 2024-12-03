package mx.petcare.mascotas.petcareAPI.model;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Table;
@Entity
@Table(name = "Tip")
public class Tip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tipId;
    
    @ManyToOne
    @JoinColumn(name = "PetId")
    private Pet petId;

    @NotBlank(message = "User tip title")
    @Column(name = "Title", nullable = false)
    @Size(min = 1, max = 50, message = "Title must be between 1 and 50 characters")
    private String title;

    @NotBlank(message = "User tip description")
    @Column(name = "Description", nullable = false)
    @Size(min = 1, max = 255, message = "Description must be between 1 and 255 characters")
    private String description;

    @Column(name = "date")
    private LocalDateTime date = LocalDateTime.now();

    
    

    public Integer getTipId() {
        return tipId;
    }

    public void setTipId(Integer tipId) {
        this.tipId = tipId;
    }

   

   

    public Pet getPetId() {
        return petId;
    }

    public void setPetId(Pet petId) {
        this.petId = petId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    

    @Override
    public String toString() {
        return "Tip [tipId=" + tipId +", petId=" + petId + ", title=" + title
                + ", description=" + description + ", date=" + date + "]";
    }
}


