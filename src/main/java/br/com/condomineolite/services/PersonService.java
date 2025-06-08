package br.com.condomineolite.services;

import br.com.condomineolite.dtos.person.PersonResponseDTO;
import br.com.condomineolite.models.Person;
import br.com.condomineolite.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional(readOnly = true)
    public Page<PersonResponseDTO> findAll(Pageable pageable) {
        Page<Person> personPage = personRepository.findAll(pageable);

        return personPage.map(PersonResponseDTO::new);
    }
}
