package com.raghoul.raghoultasks.dto.task;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TaskDto {
    private UUID id;
    private String name;
    private String description;
    private UUID ownerId;
}
