package com.lukefitness.lukegymbackend.controller.trainer.program;

import com.github.pagehelper.PageInfo;
import com.lukefitness.lukegymbackend.dto.OrderTypeEnum;
import com.lukefitness.lukegymbackend.dto.orderby.ProgramOrderByEnum;
import com.lukefitness.lukegymbackend.models.Program;
import com.lukefitness.lukegymbackend.service.ProgramService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainer/{trainerId}/program")
@Tag(name="Trainer - Program")
public class GetProgramController {
    @Autowired
    ProgramService programService;
    @GetMapping
    @Operation(summary = "Get programs for trainer",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "trainerId", description = "Trainer ID", required = true),
                    @io.swagger.v3.oas.annotations.Parameter(name = "pageNum", description = "Page number"),
                    @io.swagger.v3.oas.annotations.Parameter(name = "pageSize", description = "Page size"),
                    @io.swagger.v3.oas.annotations.Parameter(name = "sortBy", description = "Sort by",
                            schema=@Schema(implementation = ProgramOrderByEnum.class)),
                    @io.swagger.v3.oas.annotations.Parameter(name = "order", description = "Order",
                            schema=@Schema(implementation = OrderTypeEnum.class))},
            security = @io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearer-key"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Programs retrieved successfully",
                            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                                    schema=@Schema(implementation= PageInfo.class),
                                    array = @ArraySchema(schema = @Schema(implementation = Program.class)))),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }

    )
    public ResponseEntity<?> getPrograms(@PathVariable Integer trainerId, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "startDate") ProgramOrderByEnum sortBy, @RequestParam(defaultValue = "asc") OrderTypeEnum order) {
        return Response.success(programService.getProgramsForTrainer(trainerId, pageNum, pageSize, sortBy.getValue(), order.getValue()));
    }
}
