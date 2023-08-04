package com.lukefitness.lukegymbackend.controller.trainee.program_feedback;

import com.lukefitness.lukegymbackend.dto.request.TraineeFeedbackReq;
import com.lukefitness.lukegymbackend.service.program_feedback.ProgramFeedbackService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainee/{traineeId}/program_feedback")
@Tag(name ="Trainee - Program Feedback")
public class TraineePutProgramFeedbackController {
    @Autowired
    ProgramFeedbackService programFeedbackService;
    @PutMapping("/{feedbackId}")
    @Operation(summary = "Update program feedback",
            security = @io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key"),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Program feedback updated successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Program feedback not found")
    },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "traineeId", description = "Trainee Id", required = true,in = ParameterIn.PATH),
                    @io.swagger.v3.oas.annotations.Parameter(name = "feedbackId", description = "Feedback Id", required = true,in = ParameterIn.PATH),
            }
    )
    public ResponseEntity<?> updateProgramFeedback(@PathVariable Integer feedbackId, @PathVariable Integer traineeId, @RequestBody TraineeFeedbackReq traineeFeedbackReq) {
        return Response.success(programFeedbackService.updateTraineeFeedback(feedbackId, traineeFeedbackReq.getScore(), traineeFeedbackReq.getTraineeFeedback()));
    }
}
