package com.lukefitness.lukegymbackend.controller.admin.trainee;

import com.lukefitness.lukegymbackend.dto.response.register.TraineeResponse;
import com.lukefitness.lukegymbackend.service.AdminTraineeService;
import com.lukefitness.lukegymbackend.service.TraineeService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class contains the endpoints for getting trainees for admin, including getting trainees by page number and page size and getting trainee by trainee id.
 * also contains the endpoints for searching trainees by name and email.
 * The page number and page size are required to get the trainees.
 * The trainee id is required to get a specific trainee.
 */
@RestController
@RequestMapping("/admin/trainee")
@Tag(name = "Admin-Trainee Controller")
public class GetTraineeController {
    @Autowired
    private TraineeService traineeService;

    @Autowired
    AdminTraineeService adminTraineeService;

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
        return Response.success(adminTraineeService.getTraineesByPage(pageNumber, pageSize));
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
            return Response.success(adminTraineeService.getTraineesBySearchUsername(username, pageNumber, pageSize));
        }
        else if(email!=null) {
            return Response.success(adminTraineeService.getTraineesBySearchEmail(email, pageNumber, pageSize));
        }
        else
            return Response.badRequest("Username or email is required");
    }
}
