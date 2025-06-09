package br.com.condomineolite.services;

import br.com.condomineolite.dtos.person.PersonRequestDTO;
import br.com.condomineolite.dtos.person.PersonResponseDTO;
import br.com.condomineolite.models.Person;
import br.com.condomineolite.models.Resident;
import br.com.condomineolite.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional(readOnly = true)
    public Page<PersonResponseDTO> findAll(Pageable pageable) {
        Page<Person> personPage = personRepository.findAll(pageable);
        return personPage.map(PersonResponseDTO::new);
    }

    @Transactional(readOnly = true)
    public Optional<PersonResponseDTO> findById(Long id) {
        return personRepository.findById(id).map(PersonResponseDTO::new);
    }

    @Transactional
    public PersonResponseDTO create(PersonRequestDTO dto) {
        Person person = new Resident();
        person.setName(dto.getName());
        person.setEmail(dto.getEmail());
        person.setPhone(dto.getPhone());
        person = personRepository.save(person);
        return new PersonResponseDTO(person);
    }

    @Transactional
    public Optional<PersonResponseDTO> update(Long id, PersonRequestDTO dto) {
        Optional<Person> personOpt = personRepository.findById(id);
        if (personOpt.isEmpty()) {
            return Optional.empty();
        }
        Person person = personOpt.get();
        person.setName(dto.getName());
        person.setEmail(dto.getEmail());
        person.setPhone(dto.getPhone());
        Person updated = personRepository.save(person);
        return Optional.of(new PersonResponseDTO(updated));
    }

    @Transactional
    public boolean delete(Long id) {
        if (!personRepository.existsById(id)) {
            return false;
        }
        personRepository.deleteById(id);
        return true;
    }
}
