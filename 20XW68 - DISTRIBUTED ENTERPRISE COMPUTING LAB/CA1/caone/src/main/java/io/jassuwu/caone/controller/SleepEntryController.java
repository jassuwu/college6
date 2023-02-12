package io.jassuwu.caone.controller;

import java.util.HashMap;
import java.util.List;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import io.jassuwu.caone.model.SleepEntry;
import io.jassuwu.caone.service.SleepEntryService;

@Controller
public class SleepEntryController {

    @Autowired
    private SleepEntryService sleepEntryService;

    @GetMapping("/")
    public String index(Model model) {

        List<SleepEntry> sleepEntries = sleepEntryService.findAll();

        HashMap<Long, Long> sleepEntryDurations = new HashMap<>();

        for (SleepEntry sleepEntry : sleepEntries) {
            Instant sleepTime = Instant.parse(sleepEntry.getSleepTime() + ":00.00Z");
            Instant wakeTime = Instant.parse(sleepEntry.getWakeTime() + ":00.00Z");
            Duration duration = Duration.between(sleepTime, wakeTime);
            sleepEntryDurations.put(sleepEntry.getId(), duration.toHours());
        }

        model.addAttribute("sleepEntries", sleepEntryService.findAll());
        model.addAttribute("sleepEntryDurations", sleepEntryDurations);
        return "index";
    }

    @GetMapping("/new")
    public String newEntry(Model model) {
        SleepEntry sleepEntry = new SleepEntry();
        model.addAttribute("sleepEntry", sleepEntry);
        return "new_entry";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("sleepEntry") SleepEntry sleepEntry) {
        sleepEntryService.saveEntry(sleepEntry);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateEntry(@PathVariable(value = "id") long id, Model model) {
        SleepEntry sleepEntry = sleepEntryService.getEntryById(id);
        model.addAttribute("sleepEntry", sleepEntry);
        return "update_entry";
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute("sleepEntry") SleepEntry sleepEntry) {
        sleepEntryService.saveEntry(sleepEntry);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {
        this.sleepEntryService.deleteEntryById(id);
        return "redirect:/";
    }

}
