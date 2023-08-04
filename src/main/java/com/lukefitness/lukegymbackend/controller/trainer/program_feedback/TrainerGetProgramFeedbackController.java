package com.lukefitness.lukegymbackend.controller.trainer.program_feedback;

import com.lukefitness.lukegymbackend.models.ProgramFeedback;
import com.lukefitness.lukegymbackend.service.program_feedback.ProgramFeedbackService;
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
@RequestMapping("/trainer/{trainerId}/program_feedback")
@Tag(name ="Trainer - Program Feedback")
public class TrainerGetProgramFeedbackController {
    @Autowired
    ProgramFeedbackService programFeedbackService;

    @GetMapping("/program/{programId}")
    @Operation(summary = "Get program feedback by program id",
            security = @io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key"),
    parameters = {
            @Parameter(name = "trainerId", description = "Trainer id", in = ParameterIn.PATH, required = true),
            @Parameter(name = "programId", description = "Program id", in = ParameterIn.PATH, required = true)
    },
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Program feedback retrieved successfully",
                            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ProgramFeedback.class))),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    public ResponseEntity<?> getProgramFeedbackByProgram(@PathVariable Integer programId, @PathVariable Integer trainerId) {
        return Response.success(programFeedbackService.getProgramFeedbackByProgram(programId));
    }

    @Operation(
        summary="Get program feedback by feedback id",
        security=@SecurityRequirement(name="bearer-key"),
        parameters={
            @Parameter(name="trainerId", description="Trainer id", in=ParameterIn.PATH, required=true),
            @Parameter(name="feedbackId", description="Feedback id", in=ParameterIn.PATH, required=true)
        },
        responses={
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode="200", description="Program feedback retrieved successfully",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType="application/json",schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ProgramFeedback.class))),
            @ApiResponse(responseCode="400", description="Bad request"),
            @ApiResponse(responseCode="401", description="Unauthorized"), @ApiResponse(responseCode="404", description="Not found")
        }
    )
    @GetMapping("/{feedbackId}")
    public ResponseEntity<?> getProgramFeedbackByFeedbackId(@PathVariable Integer feedbackId, @PathVariable Integer trainerId) {
        return Response.success(programFeedbackService.getProgramFeedbackByFeedbackId(feedbackId));
    }
}
