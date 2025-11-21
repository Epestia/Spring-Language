package be.ipam.language.bll.services;

import be.ipam.language.bll.exceptions.UserException;
import be.ipam.language.bll.services.Iservices.IUserService;
import be.ipam.language.dao.entities.RoleEntity;
import be.ipam.language.dao.entities.UserEntity;
import be.ipam.language.dao.repositories.RoleRepository;
import be.ipam.language.dao.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Scope("singleton")
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserEntity> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findUserById(Long id) throws UserException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserException("User non trouvé avec l'ID: " + id));
    }

    @Override
    @Transactional
    public UserEntity saveUser(UserEntity user) {
        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        Set<RoleEntity> transientRoles = user.getRoles();
        Set<RoleEntity> persistentRoles = new HashSet<>();

        if (transientRoles != null && !transientRoles.isEmpty()) {
            for (RoleEntity role : transientRoles) {
                if (role == null || role.getName() == null || role.getName().trim().isEmpty()) {
                    throw new RuntimeException("Le rôle fourni est vide ou invalide.");
                }

                RoleEntity existingRole = roleRepository.findByName(role.getName())
                        .orElseThrow(() -> new RuntimeException("Rôle '" + role.getName() + "' non trouvé en base de données."));

                persistentRoles.add(existingRole);
            }
        } else {
            RoleEntity defaultRole = roleRepository.findByName("USER")
                    .orElseThrow(() -> new RuntimeException("Rôle par défaut 'USER' non trouvé en base de données."));
            persistentRoles.add(defaultRole);
        }

        user.setRoles(persistentRoles);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) throws UserException {
        if (!userRepository.existsById(id)) {
            throw new UserException("Impossible de supprimer. User non trouvé avec l'ID: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserEntity findByEmail(String email) throws UserException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException("Utilisateur non trouvé avec l'email: " + email));
    }

    public RoleEntity findRoleByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role not found: " + name));
    }

}
