package com.exercise.project.logfile.Assignment.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventData {

    private String id;

    private long duration;

    private String type;

    private String host;

    private boolean alert;
}
