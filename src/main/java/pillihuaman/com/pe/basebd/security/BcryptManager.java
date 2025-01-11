package pillihuaman.com.pe.basebd.security;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class BcryptManager {
 
    private final PasswordEncoder passwordEncoder;
 

    public BcryptManager(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
 

    public static PasswordEncoder passwordencoder(){
        return new BCryptPasswordEncoder();
    }
}