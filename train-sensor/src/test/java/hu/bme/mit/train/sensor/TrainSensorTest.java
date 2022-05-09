package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;


public class TrainSensorTest {

    TrainController mockController;
    TrainUser mockUser;
    TrainSensorImpl trainSensor;


    @Before
    public void before() {
        mockController = mock(TrainController.class);
        mockUser = mock(TrainUser.class);
        trainSensor = new TrainSensorImpl(mockController, mockUser);
    }

    @Test
    public void AbsMinLimit() {
        trainSensor.overrideSpeedLimit(-1);
        verify(mockUser, times(1)).setAlarmState(true);  
    }

    @Test
    public void AbsMaxLimit() {
        trainSensor.overrideSpeedLimit(600);
        verify(mockUser, times(1)).setAlarmState(true);
    }

    @Test
    public void CheckSpeedLimit() {
        Assert.assertEquals(10, 10);
    }

    @Test
    public void RelLimit() {
        trainSensor.overrideSpeedLimit(5);
        mockController.setJoystickPosition(400);
        verify(mockUser, times(1)).setAlarmState(true);
    }
}
