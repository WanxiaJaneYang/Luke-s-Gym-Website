package com.lukefitness.lukegymbackend.controller;

import com.lukefitness.lukegymbackend.models.Trainer;
import com.lukefitness.lukegymbackend.service.TrainerService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import com.lukefitness.lukegymbackend.utils.JWTUtils;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/trainer")
public class TrainerController {
    @Autowired
    TrainerService trainerService;

    @PostMapping("/register")
    @Operation(summary = "Register a new trainer",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "trainer json, including username, email, password and admin",
                    required = true,
                    content=@Content(
                            schema=@Schema(implementation = Trainer.class),
                            mediaType = "application/json",
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"username\":\"trainer1\",\"email\":\"trainer1@example.com\",\"password\":\"123456\"}"
                    ))))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Successfully created a new trainer",content = {@Content(schema = @Schema(implementation = Trainer.class))}),
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
        }catch (RuntimeException e){
            return Response.error(HttpStatus.BAD_REQUEST, e.getMessage());
        }catch (Exception e){
            return Response.error(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Login as a trainer",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "trainer json, including username and password",
                    required = true,
                    content=@Content(
                            schema=@Schema(implementation = Trainer.class),
                            mediaType = "application/json",
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{\"username\":\"trainer1\",\"password\":\"123456\"}"
                    )))
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully logged in as a trainer",content = {@Content(schema = @Schema(implementation = Trainer.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity trainerLogin(@RequestBody Trainer trainer){
        try {
            Trainer trainerTemp = trainerService.trainerLogin(trainer.getUsername(), trainer.getPassword());
            String token = JWTUtils.getToken(String.valueOf(trainerTemp.getId()), trainerTemp.getUsername(), String.valueOf(trainerTemp.is_admin()));
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("id", trainerTemp.getId());
            resultMap.put("username", trainerTemp.getUsername());
            resultMap.put("email", trainerTemp.getEmail());
            resultMap.put("token", token);
            return Response.success(resultMap);
        }catch (RuntimeException e){
            return Response.error(HttpStatus.UNAUTHORIZED, e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            return Response.error(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
