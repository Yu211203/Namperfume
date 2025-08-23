package com.ex.namperfume.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "tables")
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID transport_id;
    String transportName;

    @OneToOne
    @JoinColumn(name = "order_id")
    Order order;
}
