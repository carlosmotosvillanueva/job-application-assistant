package com.carlos.jobs.model;

import jakarta.validation.constraints.NotBlank;

public record OfferRequest(
        @NotBlank String company,
        @NotBlank String title,
        @NotBlank String description,
        String url,
        String language
) {}
