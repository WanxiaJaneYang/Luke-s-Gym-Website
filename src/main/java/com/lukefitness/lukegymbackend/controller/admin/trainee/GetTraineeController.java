package com.lukefitness.lukegymbackend.controller.admin.trainee;

import com.lukefitness.lukegymbackend.models.response.register.TraineeResponse;
import com.lukefitness.lukegymbackend.service.TraineeService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/trainee")
@Tag(name = "Admin-Trainee Controller")
public class GetTraineeController {
    @Autowired
    private TraineeService traineeService;

    @Operation(summary= "Get trainees by page number and page size",
            security = @SecurityRequirement(name="bearer-key"),
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "pageNumber", description = "Page number"),
                    @io.swagger.v3.oas.annotations.Parameter(name = "pageSize", description = "Page size")
            }
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved trainees",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TraineeResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/list")
    public ResponseEntity<?> getTrainees(@RequestParam int pageNumber, @RequestParam int pageSize) {
        List<TraineeResponse> result=traineeService.getTraineesByPage(pageNumber, pageSize);
        return Response.success(result);
    }

    @Operation(summary= "Get trainee by trainee id",
            security = @SecurityRequirement(name="bearer-key"),
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "traineeId", description = "Trainee id")
            }
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved trainee",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TraineeResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trainee id not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{traineeId}")
    public ResponseEntity<?> getTraineeById(@PathVariable int traineeId) {
        TraineeResponse result=new TraineeResponse(traineeService.getTraineeById(traineeId));
        return Response.success(result);
    }

    @Operation(summary = "search Trainee by username or email",
            security = @SecurityRequirement(name="bearer-key"),
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "username", description = "Trainee username"),
                    @io.swagger.v3.oas.annotations.Parameter(name = "email", description = "Trainee email")
            }
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved trainee",
                    content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TraineeResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trainee id not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/search")
    public ResponseEntity<?> searchTrainee(@RequestParam(required = false) String username,
                                                  @RequestParam(required = false) String email,
                                                  @RequestParam int pageNumber,
                                                  @RequestParam int pageSize) {
        if(username!=null) {
            List<TraineeResponse> result=traineeService.getTraineesBySearchUsername(username, pageNumber, pageSize);
            return Response.success(result);
        }
        else if(email!=null) {
            List<TraineeResponse> result=traineeService.getTraineesBySearchEmail(email, pageNumber, pageSize);
            return Response.success(result);
        }
        else
            return Response.badRequest("Username or email is required");
    }
}
