package com.ex.namperfume.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Role {
    @Id
    String role_name;
    String role_description;

    @ManyToMany
    Set<Permission> permissions;

    @ManyToMany(mappedBy = "roles")
    Set<User> users;
}
