package com.carlos.jobs.model;

public record OfferAnalysis(
        String language,
        String cvVariant,
        String matchSummary,
        String coverLetter,
        String reviewNotice
) {}
