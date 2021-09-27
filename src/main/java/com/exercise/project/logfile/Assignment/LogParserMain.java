package com.exercise.project.logfile.Assignment;

import com.exercise.project.logfile.Assignment.services.LoggerService;
import com.exercise.project.logfile.Assignment.services.LoggerServiceImpl;

public class LogParserMain {

    public static void main(String[] args) {
        LoggerService loggerService = new LoggerServiceImpl();
        System.out.println(loggerService.manipulateLogData("D:\\GitHub Projects\\LogParser\\src\\main\\resources\\logfile.txt"));
    }
}
