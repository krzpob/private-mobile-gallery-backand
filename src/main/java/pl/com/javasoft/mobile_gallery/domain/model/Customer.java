package pl.com.javasoft.mobile_gallery.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
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
        return this.password.equals(password);
    }

    

    public Customer(String email,String  password){
        this.email=email;
        this.password=password;
    }
}
