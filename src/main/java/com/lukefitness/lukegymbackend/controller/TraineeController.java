package com.lukefitness.lukegymbackend.controller;

import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.exception.NotFoundException;
import com.lukefitness.lukegymbackend.models.Trainee;
import com.lukefitness.lukegymbackend.service.TraineeService;
import com.lukefitness.lukegymbackend.utils.JWTUtils;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/trainee")
public class TraineeController {
    @Autowired
    TraineeService traineeService;

    @Operation(summary = "Login as a trainee",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "trainee json, including username and password",
                    required = true,
                    content=@Content(
                            schema=@Schema(implementation = Trainee.class),
                            mediaType = "application/json",
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"username\":\"trainee1\",\"password\":\"123456\"}"
                    ))))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully login as a trainee, the response includes id and a token"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Trainee not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/login")
    public ResponseEntity<?> traineeLogin(@RequestBody Trainee trainee) {
        try {
            Trainee traineeFromDb = traineeService.traineeLogin(trainee.getUsername(), trainee.getPassword());
            String token = JWTUtils.getToken(String.valueOf(traineeFromDb.getId()), traineeFromDb.getUsername(), "trainee");
            Map<String, String> result=Map.of("token", token, "username", trainee.getUsername(), "id", String.valueOf(trainee.getId()));
            return Response.success(result);
        } catch (BadRequestException e) {
            return Response.badRequest(e.getMessage());
        }catch(NotFoundException e){
            return Response.notFound(e.getMessage());
        } catch (Exception e) {
            return Response.internalServerError(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getTraineeByUsername(@RequestParam String username) {

        return null;
    }

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
    @PostMapping("/register")
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
