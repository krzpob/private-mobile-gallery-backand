package pl.com.javasoft.mobile_gallery.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.util.DigestUtils;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
    
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
     
    private String password;

    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Session> sessions = new ArrayList<>();

    public List<Session> getSessions(){
        return Collections.unmodifiableList(sessions);
    }
    
    public Integer getId(){
        return id;
    }

    public String getEmail(){
        return email;
    }

    public boolean checkPassword(String password){
        return this.password.equals(encodePassword(password));
    }

    
    protected Customer(){

    }

    public Customer(String email,String  password){
        this.email=email;
        this.password=encodePassword(password);
    }

    private String encodePassword(String password){
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
