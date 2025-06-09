package br.com.condomineolite.repositories;

import br.com.condomineolite.models.HistoricHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricHouseRepository extends JpaRepository<HistoricHouse, Long> {
}

