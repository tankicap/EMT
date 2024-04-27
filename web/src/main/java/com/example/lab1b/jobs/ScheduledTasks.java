package com.example.lab1b.jobs;

import com.example.lab1b.service.HousingService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private final HousingService housingService;

    public ScheduledTasks(HousingService housingService) {
        this.housingService = housingService;
    }

    @Scheduled(fixedDelay = 5000)
    public void refreshMaterializedView() {
        housingService.refreshMaterializedView();
    }

}
