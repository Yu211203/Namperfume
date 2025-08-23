package com.ex.namperfume.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID type_id;
    String type_name;

    @OneToMany(mappedBy = "type")
    List<Product> products;
}
