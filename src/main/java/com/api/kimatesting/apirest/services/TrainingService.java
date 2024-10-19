package com.api.kimatesting.apirest.services;

import com.api.kimatesting.apirest.models.TrainingModel;
import com.api.kimatesting.apirest.repositories.ITrainingRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {

    private ITrainingRepository trainingRepository;

    @Autowired
    public TrainingService(ITrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public TrainingModel createTraining(TrainingModel trainingModel){
        return trainingRepository.save(trainingModel);
    }

    public List<TrainingModel> getTrainings() {
        return trainingRepository.findAll();
    }

    public Optional<TrainingModel> getTrainingById(Long trainingId){
        return trainingRepository.findById(trainingId);
    }

    public TrainingModel updateTrainingById(Long trainingId, TrainingModel trainingModel){

        TrainingModel currentTrainingModel = trainingRepository.findById(trainingId)
                .orElseThrow( () -> new EntityExistsException(String.format("Training with id %s not found", trainingId)));

        currentTrainingModel.setName(trainingModel.getName());
        currentTrainingModel.setIcon(trainingModel.getIcon());

        return trainingRepository.save(currentTrainingModel);
    }

    public void deleteTrainingById(Long trainingId){
        trainingRepository.deleteById(trainingId);
    }

    public  Optional<TrainingModel> getTrainingByName(String trainingName) {
        return trainingRepository.findByName(trainingName);
    }
}
