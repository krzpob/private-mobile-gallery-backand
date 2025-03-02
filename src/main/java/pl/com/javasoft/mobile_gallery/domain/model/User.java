package pl.com.javasoft.mobile_gallery.domain.model;

import org.springframework.util.DigestUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
     
    private String password;

    public User(String email, String passsword){
        this.email=email;
        this.password=encodePassword(passsword);
    }

    public boolean checkPassword(String password){
        return this.password.equals(encodePassword(password));
    }
    
    private String encodePassword(String password){
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }

}
