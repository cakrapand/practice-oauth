package org.example.practiceoauth.task;

import org.example.practiceoauth.BaseResponse;
import org.example.practiceoauth.task.dto.CreateTaskDto;
import org.example.practiceoauth.task.dto.ResponseTaskDto;
import org.example.practiceoauth.task.dto.UpdateTaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public ResponseEntity<BaseResponse<List<ResponseTaskDto>>> getTasks(JwtAuthenticationToken principal) {
        String userEmail = principal.getTokenAttributes().get("email").toString();
        List<ResponseTaskDto> tasks = taskService.getTasksByEmail(userEmail);
        BaseResponse<List<ResponseTaskDto>> body = new BaseResponse<>(true, "OK", tasks);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ResponseTaskDto>> getTaskById(@PathVariable String id) {
        ResponseTaskDto task = taskService.getTaskById(id);
        BaseResponse<ResponseTaskDto> body = new BaseResponse<>(true, "OK", task);
        return ResponseEntity.ok(body);
    }

    @PostMapping()
    public ResponseEntity<BaseResponse<ResponseTaskDto>> createTask(@RequestBody  CreateTaskDto createTaskDto, JwtAuthenticationToken principal) {
        String userEmail = principal.getTokenAttributes().get("email").toString();
        ResponseTaskDto task = taskService.createTask(createTaskDto, userEmail);
        BaseResponse<ResponseTaskDto> body = new BaseResponse<>(true, "CREATED", task);
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse<ResponseTaskDto>> createTask(@PathVariable String id, @RequestBody UpdateTaskDto updateTaskDto) {
        ResponseTaskDto task = taskService.updateTask(id, updateTaskDto);
        BaseResponse<ResponseTaskDto> body = new BaseResponse<>(true, "UPDATED", task);
        return ResponseEntity.ok(body);
    }


}
