package pl.com.javasoft.mobile_gallery.application.command;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Value;
import pl.com.javasoft.mobile_gallery.application.base.Command;

@Value
public class RegisterCustomerCommand implements Command<Long> {
    String email;
    String password;
    @JsonCreator
    public RegisterCustomerCommand(String email, String password) {
        this.email = email;
        this.password = password;
    }

    
}
