package com.lukefitness.lukegymbackend.controller.trainee.program_card;

import com.lukefitness.lukegymbackend.models.ProgramCardExample;
import com.lukefitness.lukegymbackend.service.ProgramCardService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainee/{traineeId}/program_card")
@Tag(name="Trainee-Program Card")
public class TraineeGetProgramCardController {
    @Autowired
    ProgramCardService programCardService;
    @GetMapping("/{cardId}/get")
    @Operation(
            security = @SecurityRequirement(name = "bearer-key"),
            summary = "Get a program card",
            parameters = {
                    @Parameter(name = "traineeId", description = "The trainee id", required = true, in = ParameterIn.PATH),
                    @Parameter(name = "cardId", description = "The card id", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Program card retrieved successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Program card not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<?> getProgramCard(@PathVariable Integer traineeId, @PathVariable Integer cardId) {
        ProgramCardExample example = new ProgramCardExample();
        example.createCriteria().andTraineeIdEqualTo(traineeId).andCardIdEqualTo(cardId);
        return Response.success(programCardService.getProgramCardByExample(example));
    }
}
