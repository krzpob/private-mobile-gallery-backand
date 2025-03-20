package pl.com.javasoft.mobile_gallery.domain.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@ToString
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;
    
    private String password;

    public User(String email, String passsword){
        this.email=email;
        this.password="{bcrypt}"+encodePassword(passsword);
        this.authorities = new HashSet<>();
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Set<Authorities> authorities;

    public boolean checkPassword(String password){
        var bcrypt = new BCryptPasswordEncoder();
        return bcrypt.matches(password, this.password);
    }   
    
    private String encodePassword(String password){
        var bcrypt = new BCryptPasswordEncoder();
        return bcrypt.encode(password);
    }


}
