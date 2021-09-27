package com.exercise.project.logfile.Assignment.dao;

import com.exercise.project.logfile.Assignment.data.EventData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class LoggerDao {

    private static final Logger logger = LoggerFactory.getLogger(LoggerDao.class);

    public static int insertEventData(EventData eventData) {
        Connection conn = null;
        Statement stmt = null;
        String db = "jdbc:hsqldb:hsql://localhost/eventData;ifexists=true";
        String user = "SA";
        String password = "";
        int result = 0;
        try {
            // Create database connection
            conn = DriverManager.getConnection(db, user, password);

            // Create and execute statement
            stmt = conn.createStatement();
            result =  stmt.executeUpdate("INSERT INTO event_data VALUES " +
                            "(" +
                            eventData.getId() +","+ eventData.getHost()+","+
                            eventData.getDuration()+"," +eventData.getType()+","+
                            eventData.isAlert() +
                             ")");
            conn.commit();
        }
        catch (SQLException e) {
            logger.error(e.getMessage());
        }
        finally {
            try {
                // Close connection
                if (conn != null)
                    conn.close();
            }
            catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        logger.debug("Inserted results: {}", result);
        return result;
    }
}
