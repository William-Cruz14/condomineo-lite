package br.com.condomineolite.controllers;

import br.com.condomineolite.dtos.customuser.CustomUserCreateDTO;
import br.com.condomineolite.dtos.customuser.CustomUserResponseDTO;
import br.com.condomineolite.services.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class CustomUserController {

    @Autowired
    private CustomUserService userService;

    @PostMapping
    public ResponseEntity<CustomUserResponseDTO> createUser(@Validated @RequestBody CustomUserCreateDTO createDTO) {
        CustomUserResponseDTO newUser = userService.createUser(createDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUser.getId()).toUri();

        return ResponseEntity.created(uri).body(newUser);
    }
}
