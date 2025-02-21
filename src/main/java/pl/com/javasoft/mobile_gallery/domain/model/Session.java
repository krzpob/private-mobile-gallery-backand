package pl.com.javasoft.mobile_gallery.domain.model;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @Getter
    @Column(name = "session_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "session_name")
    private String name;

    @Getter
    @Column(name = "session_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime sessionDate;

    @Getter
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;

    @Getter
    @OneToMany(mappedBy = "session", cascade = CascadeType.REMOVE)
    private Set<Photo> photos = new HashSet<>();
    
    public Integer getCustomerId(){
        return customer.getId();
    }

    public Session(String name, LocalDateTime sessionDate, Customer customer){
        this.customer=customer;
        this.sessionDate=sessionDate;
        this.name=name;
    }  
    
    protected Session(){}
    
}
