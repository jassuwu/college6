package io.jassuwu.caone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jassuwu.caone.model.SleepEntry;
import io.jassuwu.caone.repository.SleepEntryRepository;

@Service
public class SleepEntryServiceImpl implements SleepEntryService {

    @Autowired
    private SleepEntryRepository sleepEntryRepository;

    @Override
    public List<SleepEntry> findAll() {
        return sleepEntryRepository.findAll();

    }

    @Override
    public void saveEntry(SleepEntry sleepEntry) {
        sleepEntryRepository.save(sleepEntry);
    }

    @Override
    public SleepEntry getEntryById(long id) {
        Optional<SleepEntry> optional = sleepEntryRepository.findById(id);
        SleepEntry sleepEntry = null;
        if (optional.isPresent()) {
            sleepEntry = optional.get();
        } else {
            throw new RuntimeException(" SleepEntry not found for id :: " + id);
        }
        return sleepEntry;
    }

    @Override
    public void deleteEntryById(long id) {
        this.sleepEntryRepository.deleteById(id);
    }

}
