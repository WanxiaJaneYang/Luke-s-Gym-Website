package com.lukefitness.lukegymbackend.controller.trainee;

import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.models.Trainee;
import com.lukefitness.lukegymbackend.service.TraineeService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/trainee/register")
@Tag(name = "Trainee controller")
public class TraineeRegisterController {
    @Autowired
    TraineeService traineeService;

    @Operation(summary = "register a trainee",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "trainee json, including username and password",
                    required = true,
                    content=@Content(
                            schema=@Schema(implementation = Trainee.class),
                            mediaType = "application/json",
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"username\":\"trainee1\",\"email\":\"trainee@example.com\",\"password\":\"123456\"}"
                            ))))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Successfully register a trainee",content = {@Content(examples =@ExampleObject(value= "{\"username\":\"trainee1\",\"id\":\"1\"") )}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> traineeRegister(@RequestBody Trainee trainee) {
        try{
            Trainee traineeTemp = traineeService.traineeRegister(trainee);
            Map<String,Object> result=Map.of("id", traineeTemp.getId(), "username", traineeTemp.getUsername());
            return Response.successCreated(result);
        }catch (BadRequestException e){
            return Response.badRequest(e.getMessage());
        }catch (Exception e){
            return Response.internalServerError(e.getMessage());
        }
    }
}
