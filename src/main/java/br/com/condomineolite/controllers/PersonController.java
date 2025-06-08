package br.com.condomineolite.controllers;

import br.com.condomineolite.dtos.person.PersonResponseDTO;
import br.com.condomineolite.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<Page<PersonResponseDTO>> findAll(Pageable pageable) {
        Page<PersonResponseDTO> persons = personService.findAll(pageable);
        return ResponseEntity.ok(persons);
    }
}
