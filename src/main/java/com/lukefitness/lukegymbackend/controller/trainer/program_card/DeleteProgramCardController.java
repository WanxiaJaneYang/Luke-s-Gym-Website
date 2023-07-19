package com.lukefitness.lukegymbackend.controller.trainer.program_card;

import com.lukefitness.lukegymbackend.service.ProgramCardService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainer/{trainerId}/program_card")
@Tag(name="Trainer-Program Card")
public class DeleteProgramCardController {
    @Autowired
    ProgramCardService programCardService;
    @DeleteMapping("/{cardId}/delete")
    @Operation(summary = "Delete a program card",
            description = "Delete a program card by providing the trainer id and the card id",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "trainerId", description = "The trainer id", required = true),
                    @io.swagger.v3.oas.annotations.Parameter(name = "cardId", description = "The card id", required = true)
            },
            security = @io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key"),
            responses = {
            @ApiResponse(responseCode = "200", description = "Program card deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Program card not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<?> deleteProgramCard(@PathVariable Integer trainerId, @PathVariable Integer cardId) {
        programCardService.deleteProgramCard(cardId, trainerId);
        return Response.success();
    }
}
