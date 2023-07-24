package com.lukefitness.lukegymbackend.controller.trainer.program_card;

import com.github.pagehelper.PageInfo;
import com.lukefitness.lukegymbackend.dto.OrderTypeEnum;
import com.lukefitness.lukegymbackend.dto.orderby.ProgramCardOrderByEnum;
import com.lukefitness.lukegymbackend.models.ProgramCard;
import com.lukefitness.lukegymbackend.service.ProgramCardService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainer/{trainerId}/program_card")
@Tag(name="Trainer-Program Card")
public class GetProgramCardController {
    @Autowired
    ProgramCardService programCardService;
    @GetMapping("/{cardId}/get")
    @Operation(
            security = @SecurityRequirement(name = "bearer-key"),
            summary = "Get a program card",
            parameters = {
                    @Parameter(name = "trainerId", description = "The trainer id", required = true),
                    @Parameter(name = "cardId", description = "The card id", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Program card retrieved successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "404", description = "Program card not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<?> getProgramCard(@PathVariable Integer trainerId, @PathVariable Integer cardId) {
        return Response.success(programCardService.getProgramCard(cardId, trainerId));
    }

    @GetMapping("/get-by-page")
    @Operation(
            security = @SecurityRequirement(name = "bearer-key"),
            summary = "Get program cards by page",
            parameters = {
                    @Parameter(name = "trainerId", description = "The trainer id", required = true),
                    @Parameter(name = "page", description = "The page number", required = true),
                    @Parameter(name = "size", description = "The page size", required = true),
                    @Parameter(name = "orderBy", description = "The order by field",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ProgramCardOrderByEnum.class)
                    ),
                    @Parameter(name = "orderType", description = "The order type",
                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = OrderTypeEnum.class))
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Program cards retrieved successfully",
                            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = PageInfo.class),
                                    array = @io.swagger.v3.oas.annotations.media.ArraySchema(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ProgramCard.class))
                            )),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
            }
    )
    public ResponseEntity<?> getProgramCardsByPage(@PathVariable Integer trainerId,
                                                   @RequestParam Integer page,
                                                   @RequestParam Integer size,
                                                   @RequestParam(defaultValue = "DATE") ProgramCardOrderByEnum orderBy,
                                                   @RequestParam(defaultValue = "DESC") OrderTypeEnum orderType) {
        return Response.success(programCardService.getProgramCards(trainerId, page, size, orderBy.getValue(), orderType.getValue()));
    }
}
