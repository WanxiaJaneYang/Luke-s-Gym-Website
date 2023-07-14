package com.lukefitness.lukegymbackend.controller.trainee.trainer;

import com.lukefitness.lukegymbackend.dto.response.query.SimpleUserQueryResponse;
import com.lukefitness.lukegymbackend.exception.NotFoundException;
import com.lukefitness.lukegymbackend.models.Trainer;
import com.lukefitness.lukegymbackend.service.TrainerService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/trainee/{traineeId}/trainer")
@Tag(name = "Trainee-Trainer Controller")
public class ViewTrainerListController {
    @Autowired
    TrainerService trainerService;

    @GetMapping
    @Operation(summary = "Get a list of trainers",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "pageNumber", description = "Page number of the list to return", required = true),
                    @io.swagger.v3.oas.annotations.Parameter(name = "pageSize", description = "Number of items in a page", required = true),
                    @io.swagger.v3.oas.annotations.Parameter(name = "traineeId", description = "Id of the trainee to get the list of trainers", required = true)
            },
            security = @SecurityRequirement(name = "bearer-key"),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "List of trainers returned successfully",
                            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = List.class))
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid page number or page size"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trainee not found"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<?> getTrainerList(@PathVariable int traineeId, @RequestParam int pageNumber, @RequestParam int pageSize){
        List<Trainer> trainers = trainerService.getTrainers(pageNumber, pageSize);
        if(trainers.isEmpty())
            throw new NotFoundException("No trainers found");
        List<SimpleUserQueryResponse> response=new ArrayList<>();
        for(Trainer trainer:trainers){
            response.add(new SimpleUserQueryResponse(trainer));
        }
        return Response.success(response);
    }
}
