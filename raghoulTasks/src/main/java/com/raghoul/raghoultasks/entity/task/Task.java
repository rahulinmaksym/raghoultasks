package com.raghoul.raghoultasks.entity.task;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Document
public class Task {

    @Id
    private UUID id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private UUID ownerId;

    private List<UUID> followersIds;
}
