package com.ex.namperfume.repository;

import com.ex.namperfume.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByUserEmail(String email);
    Optional<User> findByUserEmail(String email);

}
