package io.jassuwu.caone.service;

import java.util.List;

import io.jassuwu.caone.model.SleepEntry;

public interface SleepEntryService {
    List<SleepEntry> findAll();

    void saveEntry(SleepEntry sleepEntry);

    SleepEntry getEntryById(long id);

    void deleteEntryById(long id);
}
