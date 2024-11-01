package org.example.practiceoauth.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTaskDto {

    private String id;

    private String userId;

    private String name;

    private String description;

    private String status;

    private Date startDate;

    private Date endDate;
}
