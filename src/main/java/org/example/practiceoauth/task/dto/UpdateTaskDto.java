package org.example.practiceoauth.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskDto {

    private String name;

    private String description;

    private String status;

    private Date startDate;

    private Date endDate;
}
