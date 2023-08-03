package com.lukefitness.lukegymbackend.controller.trainee.program_feedback;

import com.lukefitness.lukegymbackend.models.ProgramFeedback;
import com.lukefitness.lukegymbackend.service.program_feedback.ProgramFeedbackService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/trainee/{traineeId}/program_feedback")
@Tag(name ="Trainee - Program Feedback")
public class TraineeProgramFeedbackController {
    @Autowired
    private ProgramFeedbackService programFeedbackService;
    @GetMapping("/{feedbackId}")
    @Operation(summary = "Get program feedback by feedback id",
            security = @SecurityRequirement(name = "bearer-key"),
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "traineeId", description = "Trainee Id", required = true, in = ParameterIn.PATH),
                    @io.swagger.v3.oas.annotations.Parameter(name = "feedbackId", description = "Feedback Id", required = true, in = ParameterIn.PATH)
            },
            responses = {
            @ApiResponse(responseCode = "200", description = "Program feedback retrieved successfully",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ProgramFeedback.class))),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Program feedback not found")
            }
    )
    public ResponseEntity<?> getProgramFeedbackByFeedbackId(@PathVariable Integer traineeId, @PathVariable Integer feedbackId) {
        return Response.success(programFeedbackService.getProgramFeedbackByFeedbackId(feedbackId));
    }

    @GetMapping("/program/{programId}")
    @Operation(
            summary = "Get program feedback by program id",
            security = @SecurityRequirement(name="bearer-key"),
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "traineeId", description = "Trainee Id", required = true, in = ParameterIn.PATH),
                    @io.swagger.v3.oas.annotations.Parameter(name = "programId", description = "Program Id", required = true, in = ParameterIn.PATH)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Program feedback retrieved successfully",
                            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ProgramFeedback.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Program feedback not found")
            }
    )
    public ResponseEntity<?> getProgramFeedbackByProgram(@PathVariable Integer traineeId, @PathVariable Integer programId) {
        return Response.success(programFeedbackService.getProgramFeedbackByProgram(programId));
    }

}
