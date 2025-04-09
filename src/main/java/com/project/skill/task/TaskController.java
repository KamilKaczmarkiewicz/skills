package com.project.skill.task;

import com.project.skill.task.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
class TaskController {

    private final TaskService taskService;


    @GetMapping
    Page<TaskDto> getAllTasks(
            @RequestParam(required = false) UUID personId,
            @ParameterObject @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable
    ){
        return taskService.getAllTasks(pageable, personId);
    }

    @GetMapping("/{id}")
    TaskDto getTaskById(@PathVariable UUID id){
        return taskService.getTaskById(id);
    }
    
}
