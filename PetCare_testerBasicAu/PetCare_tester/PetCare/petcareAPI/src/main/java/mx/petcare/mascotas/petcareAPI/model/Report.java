package mx.petcare.mascotas.petcareAPI.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreporte")
    private Long reportId;

    @Column(name = "idusuario", nullable = false)
    private Long userId;

    
    @NotBlank(message = "Content the information of the report")
    @Column(name = "descripcion", nullable = false, length = 500)
    @Size(min = 1, max = 500, message = "Description must be between 1 and 500 characters")
    private String description;

    @Column(name = "fecha", nullable = false)
    private String date;

    
    public Long getReportId() {
        return reportId;
    }

   
    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    
    public Long getUserId() {
        return userId;
    }

    
    public void setUserId(Long userId) {
        this.userId = userId;
    }

   
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    public String getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = date.format(formatter); 
    }

    public void setDate(String date) {
        this.date = date;
    }
}
