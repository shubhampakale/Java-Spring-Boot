package org.example.task;

import jakarta.validation.constraints.NotBlank;

public class TaskCreateRequest {
    @NotBlank
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
