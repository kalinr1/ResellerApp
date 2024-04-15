package com.example.examprep2.repositories;

import com.example.examprep2.models.entities.Condition;
import com.example.examprep2.models.enums.ConditionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {

    Optional<Condition> findConditionByName (ConditionEnum name);

}
