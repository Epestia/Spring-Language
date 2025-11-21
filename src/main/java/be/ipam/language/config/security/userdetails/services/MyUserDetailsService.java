package be.ipam.language.config.security.userdetails.services;

import be.ipam.language.config.security.userdetails.MyUserDetails;
import be.ipam.language.config.security.userdetails.services.Iservices.IMyUserDetailsService;
import be.ipam.language.dao.entities.UserEntity;
import be.ipam.language.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements IMyUserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with email: " + username
                ));

        return MyUserDetails.fromUserEntity(user);
    }
}
