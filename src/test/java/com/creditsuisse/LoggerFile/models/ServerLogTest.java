package com.creditsuisse.LoggerFile.models;



import static org.junit.Assert.assertNotNull;

import com.creditsuisse.LoggerFile.Models.LogServerModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ServerLogTest.class)
@RunWith(MockitoJUnitRunner.class)
public class ServerLogTest {

    @Test
    public void testServerLogs() {
        LogServerModel logServerModel =new LogServerModel();
        logServerModel.setHost("123");
        logServerModel.setId("id");
        logServerModel.setState("STARTED");
        logServerModel.setTimestamp(1L);
        logServerModel.setType("APPLICATION_LOG");

        assertNotNull(logServerModel.getHost());
        assertNotNull(logServerModel.getId());
        assertNotNull(logServerModel.getState());
        assertNotNull(logServerModel.getTimestamp());
        assertNotNull(logServerModel.getType());
    }
}
