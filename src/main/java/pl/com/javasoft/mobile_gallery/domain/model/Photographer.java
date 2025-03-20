package pl.com.javasoft.mobile_gallery.domain.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "photographers" )
@PrimaryKeyJoinColumn
public class Photographer extends User {

    public Photographer(String email, String password, String site){
        super(email, password);
        this.site=site;
    }
    
    @OneToMany(mappedBy = "photographer")
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
