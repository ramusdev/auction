package com.rb.auction.exceptions;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
// @AllArgsConstructor(onConstructor = @__(@JsonCreator))
public class ErrorResponse {
    String message;
    LocalDateTime timestamp;
}
