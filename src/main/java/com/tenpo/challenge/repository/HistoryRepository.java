package com.tenpo.challenge.repository;

import com.tenpo.challenge.domain.models.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<Record, Long> {

    @Query(value = "SELECT * FROM Record r ORDER BY r.created_date desc limit 1", nativeQuery = true)
    Optional<Record> getLastRecordInserted();
}
