package com.exercise.project.logfile.Assignment.services;

import com.exercise.project.logfile.Assignment.data.EventData;

import java.util.List;

public interface LoggerService {

    List<EventData> manipulateLogData(String fileName);
}
