package com.lukefitness.lukegymbackend.controller.trainer.program_card;

import com.lukefitness.lukegymbackend.dto.request.ProgramCardCreateReq;
import com.lukefitness.lukegymbackend.models.ProgramCard;
import com.lukefitness.lukegymbackend.service.ProgramCardService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainer/{trainerId}/program_card")
@Tag(name="Trainer-Program Card")
public class CreateProgramCardController {
    @Autowired
    ProgramCardService programCardService;

    @PostMapping("/add")
    @Operation(
            summary = "Create a program card",
            parameters = {
                    @Parameter(name = "trainerId", description = "The id of the trainer who created the program card", required = true),
            },
            security = @SecurityRequirement(name = "bearer-key"),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Program card created successfully", content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ProgramCard.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<?> createProgramCard(@PathVariable Integer trainerId, @RequestBody ProgramCardCreateReq programCardCreateReq) {
        ProgramCard programCard = new ProgramCard(programCardCreateReq);
        programCard.setTrainerId(trainerId);
        return Response.successCreated(programCardService.insertProgramCard(programCard));
    }
}
