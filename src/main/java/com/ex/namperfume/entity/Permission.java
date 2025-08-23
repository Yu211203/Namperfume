package com.ex.namperfume.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    UUID permission_id;

    String permissionName;

    String permissionDescription;
}
