package br.com.progirls.api.portal.model.dto;

public record ErrorResponse(
        int status,
        String message,
        String path,
        String timestamp
) {}