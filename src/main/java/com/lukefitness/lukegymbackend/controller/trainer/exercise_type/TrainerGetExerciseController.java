package com.lukefitness.lukegymbackend.controller.trainer.exercise_type;

import com.github.pagehelper.PageInfo;
import com.lukefitness.lukegymbackend.dto.OrderTypeEnum;
import com.lukefitness.lukegymbackend.dto.orderby.ExerciseOrderByEnum;
import com.lukefitness.lukegymbackend.models.Exercise;
import com.lukefitness.lukegymbackend.service.ExerciseService;
import com.lukefitness.lukegymbackend.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trainer/{trainerId}/exercise")
@Tag(name="Trainer-Exercise")
public class TrainerGetExerciseController {
    @Autowired
    ExerciseService exerciseService;

    @Operation(summary="get all excercise for trainer",
            security = @SecurityRequirement(name = "bearer-key"),
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "trainerId", description = "Id of the trainer", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of exercises returned successfully",
                        content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                                schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = List.class),
                                array = @io.swagger.v3.oas.annotations.media.ArraySchema(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Exercise.class)))),
                    @ApiResponse(responseCode = "400", description = "Invalid page number or page size"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/all")
    public ResponseEntity<?> getAllExercises() {
        return Response.success(exerciseService.getAllExercises());
    }

    @GetMapping("/by-page")
    @Operation(
            summary = "get all excercise by page for trainer",
            security = @SecurityRequirement(name = "bearer-key"),
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "pageNo", description = "page number", required = true),
                    @io.swagger.v3.oas.annotations.Parameter(name = "pageSize", description = "page size", required = true),
                    @io.swagger.v3.oas.annotations.Parameter(name = "trainerId", description = "Id of the trainer", required = true, in= ParameterIn.PATH),
                    @io.swagger.v3.oas.annotations.Parameter(name = "orderBy", description = "order by",schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ExerciseOrderByEnum.class)),
                    @io.swagger.v3.oas.annotations.Parameter(name = "orderType", description = "order type",schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = OrderTypeEnum.class))
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of exercises returned successfully",
                            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = PageInfo.class),
                                    array = @io.swagger.v3.oas.annotations.media.ArraySchema(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Exercise.class)))),
            }
    )
    public ResponseEntity<?> getAllExercisesByPage(@RequestParam(defaultValue = "0") Integer pageNo,
                                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                                   @RequestParam(defaultValue = "UPDATE_AT")ExerciseOrderByEnum orderBy,
                                                    @RequestParam(defaultValue = "DESC") OrderTypeEnum orderType
                                                   ) {
        return Response.success(exerciseService.getExercisesByPage(pageNo, pageSize, orderBy.getValue(), orderType.getValue()));
    }

    @GetMapping("/search")
    @Operation(
            summary = "search exercises by name for trainer",
            security = @SecurityRequirement(name="bearer-key"),
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "name", description = "exercise name", required = true),
                    @io.swagger.v3.oas.annotations.Parameter(name = "pageNo", description = "page number", required = true),
                    @io.swagger.v3.oas.annotations.Parameter(name = "pageSize", description = "page size", required = true),
                    @io.swagger.v3.oas.annotations.Parameter(name = "trainerId" ,description = "Id of the trainer", required = true),
                    @io.swagger.v3.oas.annotations.Parameter(name = "orderBy", description = "order by",schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ExerciseOrderByEnum.class)),
                    @io.swagger.v3.oas.annotations.Parameter(name = "orderType", description = "order type",schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = OrderTypeEnum.class))
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of exercises returned successfully",
                            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json",
                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = PageInfo.class),
                                    array = @io.swagger.v3.oas.annotations.media.ArraySchema(schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Exercise.class)))),
                    @ApiResponse(responseCode = "400", description = "Invalid page number or page size"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<?> getExercisesBySearch(@RequestParam String name,
                                                  @RequestParam(defaultValue = "1") Integer pageNo,
                                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                                  @RequestParam(defaultValue = "UPDATE_AT")ExerciseOrderByEnum orderBy,
                                                  @RequestParam(defaultValue = "DESC") OrderTypeEnum orderType
                                                  ) {
        return Response.success(exerciseService.getExercisesBySearch(name, pageNo, pageSize, orderBy.getValue(), orderType.getValue()));
    }
}
