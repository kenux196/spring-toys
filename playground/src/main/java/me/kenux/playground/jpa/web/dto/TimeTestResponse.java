package me.kenux.playground.jpa.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@Builder
public class TimeTestResponse {

    private OffsetDateTime utcOffsetDateTime;
    private OffsetDateTime seoulOffsetDateTime;
    private LocalDateTime utcLocalDateTime;
    private LocalDateTime seoulLocalDateTime;

    public static TimeTestResponse of(TimeTestRequest request) {
        return TimeTestResponse.builder()
            .utcOffsetDateTime(request.getUtcOffsetDateTime())
            .seoulOffsetDateTime(request.getSeoulOffsetDateTime())
            .utcLocalDateTime(request.getUtcLocalDateTime())
            .seoulLocalDateTime(request.getSeoulLocalDateTime())
            .build();
    }
}
