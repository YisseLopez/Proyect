package mx.petcare.mascotas.petcareAPI.model;
//import org.hibernate.mapping.List;
//import java.util.List;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import org.hibernate.mapping.Set;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
//import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table (name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private int userId;

    @NotBlank(message = "Content the information of the users")
    @Column(name = "firstName", nullable = false)
    @Size(min = 1, max = 50, message = "Firstname must be between 1 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Email(message = "The email must be valid")
    @NotBlank(message = "Email is mandatory")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "The phone number must be invalid")
    @Size(min = 1, max = 20, message = "Phone number must be between 1 and 200 characters")
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 1, max = 10, message = "Password must be between 1 and 10 characters")
    @Column(name = "password", nullable = false)
    private String password;

    //private String middleName;

    //Relación One-to-Many con shareTip
    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<ShareTip> shareTips;
    //-------------------------------------------

   
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //public String getMiddleNAME() {
     //   return middleName;
   // }

   // public void setMiddleName(String middleName) {
     //   this.middleName = middleName;
    //}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Otros
    //public List<ShareTip> getShareTips(){
     ////   return shareTips;
    //}

    ////public void setShareTips(List<ShareTip> shareTips){
    //    this.shareTips = shareTips;
    //}


    //@Override
   // public String toString() {
    ////    return userId + " :: " + firstName + " :: " + middleName + " :: " + email + " :: " + phone + " :: " + password;
    //}
}
