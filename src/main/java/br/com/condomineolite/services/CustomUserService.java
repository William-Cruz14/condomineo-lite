package br.com.condomineolite.services;

import br.com.condomineolite.dtos.customuser.CustomUserCreateDTO;
import br.com.condomineolite.dtos.customuser.CustomUserResponseDTO;
import br.com.condomineolite.models.CustomUser;
import br.com.condomineolite.models.Role;
import br.com.condomineolite.repositories.CustomUserRepository;
import br.com.condomineolite.repositories.RoleRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
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

    @Transactional(readOnly = true)
    public Page<CustomUserResponseDTO> findAll(Pageable pageable) {
        Page<CustomUser> userPage = userRepository.findAll(pageable);
        return userPage.map(CustomUserResponseDTO::new);
    }


    @Transactional(readOnly = true)
    public Optional<CustomUserResponseDTO> findById(Long id) {
        return userRepository.findById(id)
                .map(CustomUserResponseDTO::new);
    }

    @Transactional
    public Optional<CustomUserResponseDTO> update(Long id, CustomUserCreateDTO dto) {
        Optional<CustomUser> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            return Optional.empty();
        }
        CustomUser user = userOpt.get();
        user.setUsername(dto.getUsername());
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if (dto.getRoles() != null && !dto.getRoles().isEmpty()) {
            Set<Role> foundRoles = dto.getRoles().stream()
                    .map(roleName -> roleRepository.findByName(roleName)
                            .orElseThrow(() -> new RuntimeException("Erro: Role não encontrada: " + roleName)))
                    .collect(Collectors.toSet());
            user.setRoles(foundRoles);
        }
        CustomUser updatedUser = userRepository.save(user);
        return Optional.of(new CustomUserResponseDTO(updatedUser));
    }

    @Transactional
    public boolean delete(Long id) {
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }
}
