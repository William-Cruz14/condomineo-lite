package br.com.condomineolite.repositories;

import br.com.condomineolite.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
