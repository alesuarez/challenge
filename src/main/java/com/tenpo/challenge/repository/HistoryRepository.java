package com.tenpo.challenge.repository;

import com.tenpo.challenge.domain.models.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<Record, Long> {
}
