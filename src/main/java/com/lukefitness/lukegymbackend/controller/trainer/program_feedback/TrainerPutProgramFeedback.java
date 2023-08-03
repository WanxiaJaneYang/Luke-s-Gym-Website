package com.lukefitness.lukegymbackend.controller.trainer.program_feedback;

import com.lukefitness.lukegymbackend.dto.request.TrainerFeedbackReq;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainer/{trainerId}/program_feedback")
@Tag(name = "Trainer - Program Feedback")
public class TrainerPutProgramFeedback {
    @Autowired
    ProgramFeedbackService programFeedbackService;
    @PatchMapping("/{feedbackId}")
    @Operation(
            security = @SecurityRequirement(name = "bearer-key"),
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "trainerId", description = "Trainer ID", required = true, in = ParameterIn.PATH),
                    @io.swagger.v3.oas.annotations.Parameter(name = "feedbackId", description = "Feedback ID", required = true, in = ParameterIn.PATH)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Program feedback updated successfully", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ProgramFeedback.class))),
                    @ApiResponse(responseCode = "400", description = "Program feedback not updated", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
            }
    )
    public ResponseEntity<?> updateProgramFeedback(@PathVariable Integer feedbackId, @PathVariable Integer trainerId, @RequestBody TrainerFeedbackReq trainerFeedback) {

        return Response.success(programFeedbackService.updateTrainerFeedback(feedbackId, trainerFeedback.getTrainerFeedback()));
    }
}
