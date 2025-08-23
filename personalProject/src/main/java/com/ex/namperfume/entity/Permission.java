package com.ex.namperfume.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class Permission {
    @Id
    @Column(name = "permission_name")
    String permissionName;

    @Column(name = "permission_description")
    String permissionDescription;
}
