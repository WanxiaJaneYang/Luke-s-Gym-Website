package com.lukefitness.lukegymbackend.controller.trainer.trainee;

import com.github.pagehelper.PageInfo;
import com.lukefitness.lukegymbackend.dto.response.register.TraineeResponse;
import com.lukefitness.lukegymbackend.service.TraineeService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainer/{trainerId}/trainee")
@Tag(name = "Trainer-Trainee Controller")
public class TrainerGetTraineeController {
    @Autowired
    TraineeService traineeService;
    @GetMapping("/by-page")
    @Operation(summary = "a controller for trainer to get the trainees linked to him by page",
            security = @SecurityRequirement(name = "bearer-key"),
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "pageNumber", required = true, description = "the page number of the trainees"),
                    @io.swagger.v3.oas.annotations.Parameter(name = "pageSize", required = true, description = "the page size of the trainees"),
                    @io.swagger.v3.oas.annotations.Parameter(name = "trainerId", required = true, description = "the id of the trainer")
            },
            responses = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = PageInfo.class),
                            array = @io.swagger.v3.oas.annotations.media.ArraySchema(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TraineeResponse.class))
                    )),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @io.swagger.v3.oas.annotations.media.Content()),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @io.swagger.v3.oas.annotations.media.Content()),
            }

    )
    public ResponseEntity<?> getTraineeByPage(@PathVariable int trainerId, @RequestParam int pageNumber, @RequestParam int pageSize){
        return Response.success(traineeService.getTraineesByTrainerId(trainerId, pageNumber, pageSize));
    }
}
