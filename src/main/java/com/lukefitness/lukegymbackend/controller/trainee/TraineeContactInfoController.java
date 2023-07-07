package com.lukefitness.lukegymbackend.controller.trainee;

import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.exception.NotFoundException;
import com.lukefitness.lukegymbackend.models.TraineeContactInfo;
import com.lukefitness.lukegymbackend.service.TraineeContactInfoService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainee/{traineeId}/contact-info")
@Tag(name = "Trainee controller")
public class TraineeContactInfoController {
    @Autowired
    TraineeContactInfoService traineeContactInfoService;

    @GetMapping
    @Operation(summary = "Get trainee contact info by trainee id",
            security = @SecurityRequirement(name = "bearer-key"),
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter( name = "traineeId", description = "id of the trainee", required = true, example = "6")
            }
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved trainee contact info",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TraineeContactInfo.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trainee id not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })

    public ResponseEntity<?> getTraineeContactInfoByTraineeId(@PathVariable int traineeId) throws Exception {
            TraineeContactInfo contactInfo=traineeContactInfoService.getTraineeContactInfoByTraineeId(traineeId);
            return Response.success(contactInfo);
    }

    @PutMapping
    @Operation(summary = "updateTraineeContact by trainee id and a full trainee contact table",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter( name = "traineeId", description = "id of the trainee", required = true, example = "6"),
                    @io.swagger.v3.oas.annotations.Parameter( name = "traineeContactInfo", description = "trainee contact info table", required = true,
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TraineeContactInfo.class))
            },
            security = @SecurityRequirement(name = "bearer-key")
    )
    @ApiResponses(
            value = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully updated trainee contact info",
                            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TraineeContactInfo.class))
                    ),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trainee id not found"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<?> updateTraineeContact(@PathVariable int traineeId, @RequestBody TraineeContactInfo traineeContactInfo) throws Exception {
        TraineeContactInfo contactInfo=traineeContactInfoService.updateTraineeContact(traineeId, traineeContactInfo);
        return Response.success(contactInfo);
    }
}
