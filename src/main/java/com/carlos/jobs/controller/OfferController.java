package com.carlos.jobs.controller;

import com.carlos.jobs.model.OfferAnalysis;
import com.carlos.jobs.model.OfferRequest;
import com.carlos.jobs.service.OfferAnalysisService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/offers")
public class OfferController {
    private final OfferAnalysisService service;

    public OfferController(OfferAnalysisService service) { this.service = service; }

    @PostMapping("/analyze")
    public OfferAnalysis analyze(@Valid @RequestBody OfferRequest offer) { return service.analyze(offer); }
}
