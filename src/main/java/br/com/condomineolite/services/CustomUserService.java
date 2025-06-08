package br.com.condomineolite.services;

import br.com.condomineolite.dtos.customuser.CustomUserCreateDTO;
import br.com.condomineolite.dtos.customuser.CustomUserResponseDTO;
import br.com.condomineolite.models.CustomUser;
import br.com.condomineolite.models.Role;
import br.com.condomineolite.repositories.CustomUserRepository;
import br.com.condomineolite.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserService {
    @Autowired
    private CustomUserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public CustomUserResponseDTO createUser(CustomUserCreateDTO createDTO) {

        CustomUser newUser = new CustomUser();
        newUser.setUsername(createDTO.getUsername());

        newUser.setPassword(passwordEncoder.encode(createDTO.getPassword()));

        if (createDTO.getRoles() != null && !createDTO.getRoles().isEmpty()) {
            Set<Role> foundRoles = createDTO.getRoles().stream()
                    .map(roleName -> roleRepository.findByName(roleName)
                            .orElseThrow(() -> new RuntimeException("Erro: Role não encontrada: " + roleName)))
                    .collect(Collectors.toSet());
            newUser.setRoles(foundRoles);
        }

        CustomUser savedUser = userRepository.save(newUser);

        return new CustomUserResponseDTO(savedUser);
    }
}
