package br.com.condomineolite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.condomineolite.models.Trustee;
import org.springframework.stereotype.Repository;

@Repository
public interface TrusteeRepository extends JpaRepository<Trustee, Long> {
}
