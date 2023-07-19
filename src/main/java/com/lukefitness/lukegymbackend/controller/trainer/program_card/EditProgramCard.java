package com.lukefitness.lukegymbackend.controller.trainer.program_card;

import com.lukefitness.lukegymbackend.dto.request.ProgramCardCreateReq;
import com.lukefitness.lukegymbackend.models.ProgramCard;
import com.lukefitness.lukegymbackend.service.ProgramCardService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainer/{trainerId}/program_card/{cardId}/edit")
@Tag(name="Trainer-Program Card")
public class EditProgramCard {
    @Autowired
    ProgramCardService programCardService;
    @PutMapping
    @Operation(
            summary = "Edit a program card",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "trainerId", description = "The trainer id", required = true),
                    @io.swagger.v3.oas.annotations.Parameter(name = "cardId", description = "The card id", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "The program card to be edited", required = true,
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ProgramCardCreateReq.class))),
            security = @SecurityRequirement(name="bearer-key"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Program card edited successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Program card not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<?> editProgramCard(@PathVariable Integer trainerId, @PathVariable Integer cardId, @RequestBody ProgramCardCreateReq programCardCreateReq) {
        ProgramCard programCard = new ProgramCard(programCardCreateReq);
        programCard.setCardId(cardId);
        programCard.setTrainerId(trainerId);
        programCardService.updateProgramCard(programCard);
        return Response.success();
    }
}