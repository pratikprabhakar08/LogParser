package com.exercise.project.logfile.Assignment.services;

import com.exercise.project.logfile.Assignment.dao.LoggerDao;
import com.exercise.project.logfile.Assignment.data.EventData;
import com.exercise.project.logfile.Assignment.data.LogFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoggerServiceImpl implements LoggerService {

    private static final Logger logger = LoggerFactory.getLogger(LoggerServiceImpl.class);

    @Override
    public List<EventData> manipulateLogData(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        List<EventData> eventDataList = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Path.of(fileName), StandardCharsets.UTF_8)) {
            String line;
            Map<String, Timestamp> stringTimestampMap = new HashMap<>();
            while ((line = br.readLine()) != null) {
                LogFile logFile = mapper.readValue(line, LogFile.class);
                if (stringTimestampMap.containsKey(logFile.getId())) {
                    EventData.EventDataBuilder eventData = EventData.builder();
                    Timestamp timeStampForInitialLog = stringTimestampMap.get(logFile.getId());
                    long duration = Math.abs(timeStampForInitialLog.getTime() - logFile.getTimestamp().getTime());
                    eventDataList.add(eventData.id(logFile.getId()).duration(duration).host(logFile.getHost()).type(logFile.getType()).alert(duration > 4).build());
                } else {
                    stringTimestampMap.put(logFile.getId(), logFile.getTimestamp());
                }
                logger.debug("Total size of eventData: {}", eventDataList.size());
            }
            for (EventData eventData : eventDataList) {
                int result = LoggerDao.insertEventData(eventData);
                logger.debug("Total inserted eventData: {}", result);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return eventDataList;
    }
}
