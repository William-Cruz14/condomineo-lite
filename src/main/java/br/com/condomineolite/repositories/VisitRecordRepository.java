package br.com.condomineolite.repositories;

import br.com.condomineolite.models.VisitRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRecordRepository extends JpaRepository<VisitRecord, Long> {

}
