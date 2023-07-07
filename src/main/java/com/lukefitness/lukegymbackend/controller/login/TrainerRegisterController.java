package com.lukefitness.lukegymbackend.controller.login;

import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.models.Trainer;
import com.lukefitness.lukegymbackend.service.TrainerService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/trainer")
@Tag(name = "Login/Register Controller")
public class TrainerRegisterController {
    @Autowired
    TrainerService trainerService;

    @PostMapping("/register")
    @Operation(summary = "Register a new trainer",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "trainer json, including username, email, password and admin",
                    required = true,
                    content=@Content(
                            mediaType = "application/json",
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"username\":\"trainer1\",\"email\":\"trainer1@example.com\",\"password\":\"123456\"}"
                            ))))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Successfully created a new trainer",content = {@Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value= "{\"data\": {\"id\": 20,\"email\": \"trainer0@example.com\",\"username\": \"trainer0\"}}"
            ))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })

    public ResponseEntity trainerRegister(@RequestBody Trainer trainer){
        try {
            trainer.set_admin(false);
            Trainer trainerTemp = trainerService.registerTrainer(trainer);
            HashMap<String, Object> trainerMap = new HashMap<>();
            trainerMap.put("id", trainerTemp.getId());
            trainerMap.put("username", trainerTemp.getUsername());
            trainerMap.put("email", trainerTemp.getEmail());
            return Response.successCreated(trainerMap);
        }catch (BadRequestException e){
            return Response.badRequest(e.getMessage());
        }catch (Exception e){
            return Response.internalServerError(e.getMessage());
        }
    }
}
