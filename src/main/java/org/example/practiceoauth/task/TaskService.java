package org.example.practiceoauth.task;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.example.practiceoauth.task.dto.CreateTaskDto;
import org.example.practiceoauth.task.dto.ResponseTaskDto;
import org.example.practiceoauth.task.dto.UpdateTaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final Validator validator;

    @Autowired
    public TaskService(TaskRepository taskRepository, Validator validator) {
        this.taskRepository = taskRepository;
        this.validator = validator;
    }

    public List<ResponseTaskDto> getTasksByEmail(String userEmail){
        return taskRepository.findByUserEmail(userEmail).stream().map(this::toResponseTaskDto).toList();
    }

    public ResponseTaskDto getTaskById(String id){
        Task task = taskRepository.findById(id).orElse(null);

        if(task == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task Not Found");
        }

        return toResponseTaskDto(task);
    }

    public ResponseTaskDto createTask(CreateTaskDto createTaskDto, String userEmail) {
        Set<ConstraintViolation<CreateTaskDto>> constraintViolations = validator.validate(createTaskDto);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        Task newTask = new Task();
        newTask.setName(createTaskDto.getName());
        newTask.setUserEmail(userEmail);
        newTask.setDescription(createTaskDto.getDescription());
        newTask.setStatus(createTaskDto.getStatus());
        newTask.setStartDate(createTaskDto.getStartDate());
        newTask.setEndDate(createTaskDto.getEndDate());

        return toResponseTaskDto(taskRepository.save(newTask));
    }

    public ResponseTaskDto updateTask(String id, UpdateTaskDto updateTaskDto) {

        Task task = taskRepository.findById(id).orElse(null);
        if(task == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task Not Found");
        }


        task.setName(updateTaskDto.getName());
        task.setDescription(updateTaskDto.getDescription());
        task.setStatus(updateTaskDto.getStatus());
        task.setStartDate(updateTaskDto.getStartDate());
        task.setEndDate(updateTaskDto.getEndDate());

        return toResponseTaskDto(taskRepository.save(task));
    }

    private ResponseTaskDto toResponseTaskDto(Task task){
        return new ResponseTaskDto(
                task.getId(),
                task.getUserEmail(),
                task.getName(),
                task.getDescription(),
                task.getStatus(),
                task.getStartDate(),
                task.getEndDate()
        );
    }
}
