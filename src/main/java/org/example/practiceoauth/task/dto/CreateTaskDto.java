package org.example.practiceoauth.task.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String status;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;
}
