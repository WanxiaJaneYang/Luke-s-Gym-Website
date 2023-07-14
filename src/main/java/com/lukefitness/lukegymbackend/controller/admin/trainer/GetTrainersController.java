package com.lukefitness.lukegymbackend.controller.admin.trainer;

import com.lukefitness.lukegymbackend.dto.response.query.SimpleUserQueryResponse;
import com.lukefitness.lukegymbackend.models.Trainer;
import com.lukefitness.lukegymbackend.service.TrainerService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/trainer")
@Tag(name = "Admin - Trainer")
public class GetTrainersController {
    @Autowired
    private TrainerService trainerService;

    @GetMapping("/by-page")
    @Operation(summary = "Get all trainers by page and size",
            security = @SecurityRequirement(name = "bearer-key"),
            parameters={
                    @io.swagger.v3.oas.annotations.Parameter(name="pageNumber", description="Page number", required=true),
                    @io.swagger.v3.oas.annotations.Parameter(name="pageSize", description="Page size", required=true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Success",
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = List.class),
                            array = @io.swagger.v3.oas.annotations.media.ArraySchema(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = SimpleUserQueryResponse.class))
                )),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
            }
    )
    public ResponseEntity<?> getTrainers(@RequestParam int pageNumber, @RequestParam int pageSize) {
        List<Trainer> trainers = trainerService.getTrainers(pageNumber, pageSize);
        List<SimpleUserQueryResponse> response=new ArrayList<>();
        for(Trainer trainer: trainers){
            response.add(new SimpleUserQueryResponse(trainer));
        }
        return Response.success(response);
    }

    @GetMapping("/all")
    @Operation(summary = "get all trainers without paging parameters(ordered by username)",
            security = @SecurityRequirement(name = "bearer-key"),
            responses = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = List.class),
                            array = @io.swagger.v3.oas.annotations.media.ArraySchema(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = SimpleUserQueryResponse.class))
                    )),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
            }
    )
    public ResponseEntity<?> getAllTrainers(){
        List<Trainer> trainers = trainerService.getAllTrainers();
        List<SimpleUserQueryResponse> response=new ArrayList<>();
        for(Trainer trainer: trainers){
            response.add(new SimpleUserQueryResponse(trainer));
        }
        return Response.success(response);
    }

    @GetMapping("/search")
    @Operation(summary = "a search method using email or username",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "email", description = "Trainer's email", required = false),
                    @io.swagger.v3.oas.annotations.Parameter(name = "username", description = "Trainer's username", required = false),
                    @io.swagger.v3.oas.annotations.Parameter(name = "pageNumber", description = "Page number", required = true),
                    @io.swagger.v3.oas.annotations.Parameter(name = "pageSize", description = "Page size", required = true)
            },
            responses = {
            @ApiResponse(responseCode = "200", description = "Success, even when no match found",
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = SimpleUserQueryResponse.class),
                            array = @io.swagger.v3.oas.annotations.media.ArraySchema(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = SimpleUserQueryResponse.class))
                    )),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
            },
            security = @SecurityRequirement(name = "bearer-key")
    )
    public ResponseEntity<?> searchTrainers(@RequestParam(required = false) String email, @RequestParam(required = false) String username,
                                            @RequestParam int pageNumber, @RequestParam int pageSize){
        List<Trainer> trainers;
        if(email==null && username==null){
            return Response.badRequest("Please provide either email or username");
        }else if(username!=null){
            trainers=trainerService.searchTrainerByUsername(username, pageNumber, pageSize);
        }else{
            trainers=trainerService.searchTrainerByEmail(email, pageNumber, pageSize);
        }
        List<SimpleUserQueryResponse> response=new ArrayList<>();
        for(Trainer trainer: trainers){
            response.add(new SimpleUserQueryResponse(trainer));
        }
        return Response.success(response);
    }


    @Operation(summary = "get individual trainer info by trainer's id",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "trainerId", description = "Trainer's id", required = true)
            },
            security = @SecurityRequirement(name = "bearer-key"),
            responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success",
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = SimpleUserQueryResponse.class)
                    )),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
            }

    )
    @GetMapping("/{trainerId}")
    public ResponseEntity<?> getTrainerById(@PathVariable int trainerId) {
        return Response.success(new SimpleUserQueryResponse(trainerService.getTrainerById(trainerId)));
    }
}
