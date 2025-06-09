package br.com.condomineolite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.condomineolite.models.Reserve;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Long> {
}
