package com.example.projectmanagement.settings;
import lombok.Data;

@Data
public class EmployeeFilterRequest {
    private String keyword;
    private int role;
    private int status;
    private int language;
}
