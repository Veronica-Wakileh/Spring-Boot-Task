package org.exalt.training.springboottask.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ticketId;
    private String comment;
}




