package be.ipam.language.config.security.userdetails.services.Iservices;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IMyUserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
