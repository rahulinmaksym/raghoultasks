package com.raghoul.raghoultasks.entity.comment;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Document
public class Comment {

    @Id
    private UUID id;

    @NotNull
    private String comment;

    @NotNull
    private LocalDateTime creationDate;

    @NotNull
    private UUID authorId;

    private List<UUID> likedIds;

    private List<UUID> dislikedIds;
}
