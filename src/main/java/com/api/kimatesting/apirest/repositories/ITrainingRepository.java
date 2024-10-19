package com.api.kimatesting.apirest.repositories;

import com.api.kimatesting.apirest.models.TrainingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITrainingRepository extends JpaRepository<TrainingModel,Long> {
    Optional<TrainingModel> findByName(String name);
}
