package com.raghoul.raghoultasks.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@ToString
@Document
public class Task {

    @Id
    private UUID id;

    private String name;

    private String description;

    private UUID ownerId;
}
