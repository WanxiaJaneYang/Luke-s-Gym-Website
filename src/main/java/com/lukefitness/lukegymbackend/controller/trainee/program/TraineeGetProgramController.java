package com.lukefitness.lukegymbackend.controller.trainee.program;

import com.github.pagehelper.PageInfo;
import com.lukefitness.lukegymbackend.dto.OrderTypeEnum;
import com.lukefitness.lukegymbackend.dto.orderby.ProgramOrderByEnum;
import com.lukefitness.lukegymbackend.models.Program;
import com.lukefitness.lukegymbackend.models.ProgramExample;
import com.lukefitness.lukegymbackend.models.ProgramStatusEnum;
import com.lukefitness.lukegymbackend.service.ProgramService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainee/{trainerId}/program")
@Tag(name="Trainee - Program")
public class TraineeGetProgramController{
    @Autowired
    ProgramService programService;
    @GetMapping
    @Operation(summary = "Get programs for trainer",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "traineeId", description = "Trainee ID", required = true,in= ParameterIn.PATH),
                    @io.swagger.v3.oas.annotations.Parameter(name = "pageNum", description = "Page number"),
                    @io.swagger.v3.oas.annotations.Parameter(name = "pageSize", description = "Page size"),
                    @io.swagger.v3.oas.annotations.Parameter(name = "sortBy", description = "Sort by",
                            schema=@Schema(implementation = ProgramOrderByEnum.class)),
                    @io.swagger.v3.oas.annotations.Parameter(name = "order", description = "Order",
                            schema=@Schema(implementation = OrderTypeEnum.class)),
                    @io.swagger.v3.oas.annotations.Parameter(name = "trainerId", description = "Trainer ID")
            },
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
    public ResponseEntity<?> getPrograms(@PathVariable Integer traineeId, @RequestParam(required = false) Integer trainerId, @RequestParam(required = false)ProgramStatusEnum programStatusEnum, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(required = false,defaultValue = "START_DATE") ProgramOrderByEnum sortBy, @RequestParam(required = false,defaultValue = "ASC") OrderTypeEnum order) {
        ProgramExample programExample = new ProgramExample();
        ProgramExample.Criteria criteria=programExample.createCriteria().andTraineeIdEqualTo(traineeId);
        if(trainerId!=null)
            criteria.andTrainerIdEqualTo(trainerId);
        if(programStatusEnum!=null)
            criteria.andStatusEqualTo(programStatusEnum.getValue());
        return Response.success(programService.getProgramsForTrainer(programExample, pageNum, pageSize, sortBy.getValue(), order.getValue()));
    }
}
