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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@PrimaryKeyJoinColumn
@Table(name = "customers")
public class Customer extends User  {
    
    @ManyToOne()
    @JoinColumn(name = "photographer_id")
    private Photographer photographer;

    
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Session> sessions = new ArrayList<>();

    public List<Session> getSessions(){
        return Collections.unmodifiableList(sessions);
    }

    
    protected Customer(){

    }

    public Customer(String email, String password, Photographer photographer){
        super(email, password);
        this.photographer=photographer;
    }

    
    
}
