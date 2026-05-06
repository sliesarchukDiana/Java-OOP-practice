package com.example.lab_8.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubscriptionDto {
    private Integer id;

    @NotNull(message = "Format type is necessary")
    private Integer formatType;

    @NotNull(message = "Client's ID is required")
    private Integer clientId;

    private Integer sectionId;
    private Integer keywordId;
}