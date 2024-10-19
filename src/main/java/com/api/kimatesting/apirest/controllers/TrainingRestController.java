package com.api.kimatesting.apirest.controllers;

import com.api.kimatesting.apirest.models.TrainingModel;
import com.api.kimatesting.apirest.services.TrainingService;
import com.api.kimatesting.apirest.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/trainings")
public class TrainingRestController {

    private TrainingService trainingService;

    @Autowired
    public TrainingRestController(TrainingService trainingService){
        this.trainingService = trainingService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createTraining(@RequestBody TrainingModel trainingModel){
        try {
            Optional<TrainingModel> trainingModelExisted = trainingService.getTrainingByName(trainingModel.getName());
            if(trainingModelExisted.isPresent()) {
                ApiResponse response = new ApiResponse("Training already exist");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            } else {
                trainingService.createTraining(trainingModel);
                ApiResponse response = new ApiResponse(trainingModel, true,"Training created successfully");
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllTrainings(){
        try {
            List<TrainingModel> trainingModelList = trainingService.getTrainings();
            ApiResponse response = new ApiResponse(trainingModelList, true, "Trainings listed successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> getTraining(@PathVariable Long id){
        try {
            Optional<TrainingModel> trainingModel = trainingService.getTrainingById(id);
            ApiResponse response;
            if(trainingModel.isEmpty()) {
                response = new ApiResponse(trainingModel, false,  "There is not Training with id " + id);
            } else {
                response = new ApiResponse(trainingModel, true, "Training listed successfully");
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> updateTraining(@RequestBody TrainingModel trainingModel, @PathVariable Long id){
        try {
            Optional<TrainingModel> trainingModelExisted = trainingService.getTrainingById(id);
            if(trainingModelExisted.isPresent()) {
                TrainingModel trainingModelUpdated = trainingService.updateTrainingById(id, trainingModel);
                ApiResponse response = new ApiResponse(trainingModelUpdated,true, "Training updated successfully");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                ApiResponse response = new ApiResponse("Training does not exist");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> deleteTraining(@PathVariable Long id){
        try {
            Optional<TrainingModel> trainingModelExisted = trainingService.getTrainingById(id);
            if(trainingModelExisted.isPresent()) {
                trainingService.deleteTrainingById(id);
                ApiResponse response = new ApiResponse(trainingModelExisted,true, "Training deleted successfully");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } else {
                ApiResponse response = new ApiResponse("Training does not exist");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
        }
    }
}
