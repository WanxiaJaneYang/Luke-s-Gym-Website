package com.lukefitness.lukegymbackend.controller.email;

import com.lukefitness.lukegymbackend.exception.BadRequestException;
import com.lukefitness.lukegymbackend.service.VerifyService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/email")
@Tag(name = "Email controller")
public class VerifyController {
    @Autowired
    VerifyService verifyService;
    @PostMapping("/verifyEmail")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully verified email"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request:like invalid token id or token expired"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "Verify email",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "tokenId", description = "token id", required = true),
                    @io.swagger.v3.oas.annotations.Parameter(name = "token", description = "token", required = true)
            })
    public ResponseEntity<?> verifyEmail(@RequestParam int tokenId, @RequestParam String token) {
        try {
            verifyService.verifyEmail(tokenId, token);
            return Response.success("Email verified successfully");
        } catch (BadRequestException e) {
            return Response.badRequest(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(e.getMessage());
        }
    }

    @PostMapping("/verifyResetPw")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully verified reset password",
                    content = @io.swagger.v3.oas.annotations.media.Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Map.class,
                                    description = "Map of verified result",
                                    example = "{\"id\":19,\"userType\":\"trainer\"}"
                            )
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request:like invalid token id or token expired"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Operation(summary = "Verify reset password token",
            description = "Verify reset password token",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "tokenId", description = "Token id", required = true),
                    @io.swagger.v3.oas.annotations.Parameter(name = "token", description = "Token", required = true)
            }
    )
    public ResponseEntity<?> verifyResetPw(@RequestParam int tokenId, @RequestParam String token) {
        try {
            Map<String,Object> verifyResult =verifyService.verifyResetPw(tokenId, token);
            return Response.success(verifyResult);
        } catch (BadRequestException e) {
            return Response.badRequest(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return Response.internalServerError(e.getMessage());
        }
    }
}
