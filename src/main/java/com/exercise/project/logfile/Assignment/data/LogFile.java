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
public class LogFile {

    private String id;

    private String state;

    private String type;

    private String host;

    private Timestamp timestamp;
}
