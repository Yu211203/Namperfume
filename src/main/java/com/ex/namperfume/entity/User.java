package com.ex.namperfume.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.cfg.defs.UUIDDef;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID user_id;
    String firstName;
    String lastName;

    @Column(unique = true)
    String email;

    String phone;
    String password;
    LocalDate dateOfBirth;
    String shippingAddress;

    @OneToMany(mappedBy = "user")
    List<Order> orders;

    @ManyToMany
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_name"))
    private Set<Role> roles;
}

