package org.exalt.training.springboottask.model;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;

import java.time.LocalDateTime;

@Data
@Document
public class Activity {

    @Id
    private Long id;
    private Long ticketId;
    private String Action;
    private LocalDateTime createdAT;
}
