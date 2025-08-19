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
    String roleName;
    String roleDescription;

    @ManyToMany
            @JoinTable(
                    name = "role_permissions",
                    joinColumns = @JoinColumn(name = "role_name"),
                    inverseJoinColumns = @JoinColumn(name = "permission_name")
            )
    Set<Permission> permissions;

    @ManyToMany(mappedBy = "roles")
    Set<User> users;

    public Role(String role_name){
        this.roleName = role_name;
    }
}
