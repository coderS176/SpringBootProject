package com.example.journalApp.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")

//used lombok for auto generation of getter and setter methods
@Data
public class JournalEntry {
    @Id
    private ObjectId id;

    private String title;

    private String content;

    public LocalDateTime date;
}
