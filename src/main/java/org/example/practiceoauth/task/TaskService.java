package org.example.practiceoauth.task;


import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.example.practiceoauth.task.dto.CreateTaskDto;
import org.example.practiceoauth.task.dto.ResponseTaskDto;
import org.example.practiceoauth.task.dto.UpdateTaskDto;
import org.example.practiceoauth.user.User;
import org.example.practiceoauth.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    private final Validator validator;

    @Autowired
    public TaskService(TaskRepository taskRepository, UserRepository userRepository, Validator validator) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.validator = validator;
    }

    public List<ResponseTaskDto> getAllTasks(){
        return taskRepository.findAll().stream().map(this::toResponseTaskDto).toList();
    }

    public ResponseTaskDto getTaskById(String id){
        Task task = taskRepository.findById(id).orElse(null);

        if(task == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task Not Found");
        }

        return toResponseTaskDto(task);
    }

    public ResponseTaskDto createTask(CreateTaskDto createTaskDto) {
        Set<ConstraintViolation<CreateTaskDto>> constraintViolations = validator.validate(createTaskDto);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        User user = userRepository.findAll().get(0);

        Task newTask = new Task();
        newTask.setName(createTaskDto.getName());
        newTask.setDescription(createTaskDto.getDescription());
        newTask.setStatus(createTaskDto.getStatus());
        newTask.setStartDate(createTaskDto.getStartDate());
        newTask.setEndDate(createTaskDto.getEndDate());
        newTask.setUser(user);

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
                task.getUser().getId().toString(),
                task.getName(),
                task.getDescription(),
                task.getStatus(),
                task.getStartDate(),
                task.getEndDate()
        );
    }
}
