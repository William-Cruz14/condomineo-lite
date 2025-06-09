package br.com.condomineolite.controllers;

import br.com.condomineolite.dtos.customuser.CustomUserCreateDTO;
import br.com.condomineolite.dtos.customuser.CustomUserResponseDTO;
import br.com.condomineolite.services.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class CustomUserController {

    @Autowired
    private CustomUserService userService;

    @PostMapping
    public ResponseEntity<CustomUserResponseDTO> createUser(@RequestBody CustomUserCreateDTO createDTO) {
        CustomUserResponseDTO newUser = userService.createUser(createDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(uri).body(newUser);
    }

    @GetMapping
    public ResponseEntity<Page<CustomUserResponseDTO>> findAll(Pageable pageable) {
        Page<CustomUserResponseDTO> users = userService.findAll(pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomUserResponseDTO> findById(@PathVariable Long id) {
        Optional<CustomUserResponseDTO> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomUserResponseDTO> update(@PathVariable Long id, @RequestBody CustomUserCreateDTO dto) {
        Optional<CustomUserResponseDTO> updated = userService.update(id, dto);
        return updated.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (userService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}