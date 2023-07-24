package com.lukefitness.lukegymbackend.controller.trainer.program;

import com.lukefitness.lukegymbackend.service.ProgramService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainer/{trainerId}/program")
@Tag(name="Trainer - Program")
public class ModifyProgramController {
    @Autowired
    ProgramService programService;
    @PatchMapping("/{programId}/cancel")
    @Operation(summary="cancel program",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "trainerId", description = "Trainer ID", required = true, in = ParameterIn.PATH),
                    @io.swagger.v3.oas.annotations.Parameter(name = "programId", description = "Program ID", required = true, in=ParameterIn.PATH)
            },
            security = @SecurityRequirement(name = "bearer-key"),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Program cancelled successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    public ResponseEntity<?> cancelProgram(@PathVariable Integer trainerId, @PathVariable Integer programId) {
        programService.cancelProgram(trainerId, programId);
        return Response.success();
    }
}
