package pl.com.javasoft.mobile_gallery.domain.service;

public interface UserRegistrationService {

/**
 * Registers a new user with the provided email, name, and password.
 *
 * @param email    The email address of the user..
 * @param password The password for the user's account.
 */
 
    public void registerUser(String email, String password, String role); 
}
