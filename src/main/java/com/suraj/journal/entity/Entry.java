package com.suraj.journal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "entries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entry {

    @Id
    private Long id;
    private String title;
    private String content;
}
