package pl.com.javasoft.mobile_gallery.domain.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "photographers" )
public class Photographer {

    public Photographer(String site){
        this.site=site;
    }
    
    @Id
    @Column(name = "photographer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    
    @OneToMany()
    Set<Customer> customers = new HashSet<>();

    public Set<Customer> getCustomers(){
        return new HashSet<>(customers);
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    @Getter
    private String site;
    
}
