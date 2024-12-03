package mx.petcare.mascotas.petcareAPI.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Table;
@Entity
@Table(name = "ShareTip")
public class ShareTip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("shareId")
    private int shareId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "tipId", nullable = false)
    private Tip tip;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 1, max = 255, message = "Firstname must be between 1 and 50 characters")
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date")
    private LocalDateTime date = LocalDateTime.now();

    public int getShareId() {
        return shareId;
    }

    public void setShareId(int shareId) {
        this.shareId = shareId;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return shareId + " :: " + tip.getTipId() + " :: " + user.getUserId() + " :: " + description + " :: " + date;
    }
}

