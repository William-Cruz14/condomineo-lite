package br.com.condomineolite.repositories;

import br.com.condomineolite.models.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident, Long> {
}
