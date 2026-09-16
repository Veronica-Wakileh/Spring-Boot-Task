package org.exalt.training.springboottask.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ticketId;
    private String comment;
}




