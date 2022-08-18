package me.kenux.playground.jpa.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
public class CreateMemberRequest {

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX", timezone = "Asia/Seoul")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX", iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX", timezone = "UTC")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX", iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime localDateTime;
}
