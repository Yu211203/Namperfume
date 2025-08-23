package com.ex.namperfume.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID role_id;

    String roleName;
    String roleDescription;

    @ManyToMany(fetch = FetchType.EAGER, cascade={CascadeType.MERGE})
            @JoinTable(
                    name = "role_permissions",
                    joinColumns = @JoinColumn(name = "role_id"),
                    inverseJoinColumns = @JoinColumn(name = "permission_id")
            )
    Set<Permission> permissions = new HashSet<>();

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(String role_name){
        this.roleName = role_name;
        this.permissions = new HashSet<>();
    }

    public void addPermission(Permission permission){
        if(this.permissions == null)
            this.permissions = new HashSet<>();
        this.permissions.add(permission);
    }

}
