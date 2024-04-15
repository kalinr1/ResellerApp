package com.example.examprep2.repositories;

import com.example.examprep2.models.entities.Condition;
import com.example.examprep2.models.entities.Offer;
import com.example.examprep2.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameAndPassword(String username, String password);
    List<User> findAllByIdNot (Long id);
}

