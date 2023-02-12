package io.jassuwu.caone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.jassuwu.caone.model.SleepEntry;

public interface SleepEntryRepository extends JpaRepository<SleepEntry, Long> {

}
