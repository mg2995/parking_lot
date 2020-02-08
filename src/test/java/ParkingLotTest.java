import com.gojek.parkingLot.exceptions.ParkingException;
import com.gojek.parkingLot.models.Vehicle;
import com.gojek.parkingLot.models.VehicleFactory;
import com.gojek.parkingLot.models.VehicleType;
import com.gojek.parkingLot.resource.ParkingManager;
import com.gojek.parkingLot.resource.ParkingManagerImp;
import com.gojek.parkingLot.responses.ParkingStatusResponse;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ParkingLotTest {

    private ParkingManager parkingManager;
    private static final int SLOT_NUMBERS = 10;
    private VehicleFactory vehicleFactory;

    @Before
    public void initialize(){
        parkingManager = ParkingManagerImp.getParkingInstance(SLOT_NUMBERS);
        vehicleFactory = new VehicleFactory();
    }




    @Test
    public void testParkVehicle_success() throws ParkingException {
        Vehicle vehicle1 = vehicleFactory.buildVehicle("KA-01-HH-1234", Color.WHITE.toString(), VehicleType.CAR);
        Vehicle vehicle2 = vehicleFactory.buildVehicle("KA-01-HH-9999", Color.WHITE.toString(), VehicleType.CAR);
        parkingManager.park(vehicle1);
        parkingManager.park(vehicle2);
        

    }

    @Test
    public void testParkVehicle_no_available_space() throws ParkingException{
        Vehicle vehicle1 = vehicleFactory.buildVehicle("KA-01-HH-1234", Color.WHITE.toString(), VehicleType.CAR);
        Vehicle vehicle2 = vehicleFactory.buildVehicle("KA-01-HH-9999", Color.WHITE.toString(), VehicleType.CAR);
        Vehicle vehicle3 = vehicleFactory.buildVehicle("KA-01-HH-9910", Color.BLACK.toString(), VehicleType.CAR);
        ParkingManager parkingManager = ParkingManagerImp.getParkingInstance(2);
        assertEquals(1, parkingManager.park(vehicle1));
        assertEquals(2, parkingManager.park(vehicle2));
        parkingManager.park(vehicle3);
        assertEquals(2,parkingManager.getStatus().size());

    }


    @Test
    public void testleaveVehicle() throws ParkingException {
        Vehicle vehicle1 = vehicleFactory.buildVehicle("KA-01-HH-1234", Color.WHITE.toString(), VehicleType.CAR);
        Vehicle vehicle2 = vehicleFactory.buildVehicle("KA-01-HH-9999", Color.WHITE.toString(), VehicleType.CAR);
        parkingManager.park(vehicle1);
        parkingManager.park(vehicle2);
        parkingManager.leave(1);
       assertEquals(1,parkingManager.getStatus().size());
    }

    @Test
    public void testleaveVehicle_when_no_vehicle_parked() throws ParkingException{
        assertEquals(false ,parkingManager.leave(1));
    }


    @Test
    public void testgetSlotOfAGivenRegNo_success() throws ParkingException{
        Vehicle vehicle1 = vehicleFactory.buildVehicle("KA-01-HH-9991", Color.BLACK.toString(), VehicleType.CAR);
        Vehicle vehicle2 = vehicleFactory.buildVehicle("KA-01-HH-9992", Color.WHITE.toString(), VehicleType.CAR);
        Vehicle vehicle3 = vehicleFactory.buildVehicle("KA-01-HH-9993", Color.BLACK.toString(), VehicleType.CAR);
        parkingManager.park(vehicle1);
        parkingManager.park(vehicle2);
        parkingManager.park(vehicle3);
        assertEquals(2, parkingManager.getCarSlot(vehicle2.getRegistrationNo()).intValue());
        assertEquals(3, parkingManager.getCarSlot(vehicle3.getRegistrationNo()).intValue());
        assertEquals(2, parkingManager.getSlotsWithColor(Color.BLACK.toString()).size());
        assertEquals(1, parkingManager.getSlotsWithColor(Color.WHITE.toString()).size());
    }


    @Test
    public void testGetRegistrationNumberOfAColor_success() throws ParkingException{
        Vehicle vehicle1 = vehicleFactory.buildVehicle("KA-01-HH-9991", Color.BLACK.toString(), VehicleType.CAR);
        Vehicle vehicle2 = vehicleFactory.buildVehicle("KA-01-HH-9992", Color.WHITE.toString(), VehicleType.CAR);
        Vehicle vehicle3 = vehicleFactory.buildVehicle("KA-01-HH-9993", Color.BLACK.toString(), VehicleType.CAR);
        parkingManager.park(vehicle1);
        parkingManager.park(vehicle2);
        parkingManager.park(vehicle3);
        Set<String> carList = parkingManager.getRegistrationNumbers(Color.BLACK.toString());
        assertEquals(2, carList.size());

    }

    @Test
    public void testgetSlotOfAGivenRegNo_failure() throws ParkingException{
        Vehicle vehicle1 = vehicleFactory.buildVehicle("KA-01-HH-9991", Color.BLACK.toString(), VehicleType.CAR);
        parkingManager.park(vehicle1);
        assertEquals(-1, parkingManager.getCarSlot("KA-01-HH-9992").intValue());

    }





    @Test
    public void testStatusOfParkingManager() throws ParkingException{
        Vehicle vehicle1 = vehicleFactory.buildVehicle("KA-01-HH-9991", Color.BLACK.toString(), VehicleType.CAR);
        Vehicle vehicle2 = vehicleFactory.buildVehicle("KA-01-HH-9992", Color.WHITE.toString(), VehicleType.CAR);
        Vehicle vehicle3 = vehicleFactory.buildVehicle("KA-01-HH-9993", Color.BLACK.toString(), VehicleType.CAR);
        parkingManager.park(vehicle1);
        parkingManager.park(vehicle2);
        parkingManager.park(vehicle3);
        List<ParkingStatusResponse> parkingManagerStatusResponseList = parkingManager.getStatus();
        assertEquals(3, parkingManagerStatusResponseList.size());
    }

    @Test
    public void testGetRegistrationNumberOfAColor_noCarPresent() throws ParkingException{
        Vehicle vehicle1 = vehicleFactory.buildVehicle("KA-01-HH-9991", Color.BLACK.toString(), VehicleType.CAR);
        Vehicle vehicle2 = vehicleFactory.buildVehicle("KA-01-HH-9992", Color.WHITE.toString(), VehicleType.CAR);
        Vehicle vehicle3 = vehicleFactory.buildVehicle("KA-01-HH-9993", Color.BLACK.toString(), VehicleType.CAR);
        parkingManager.park(vehicle1);
        parkingManager.park(vehicle2);
        parkingManager.park(vehicle3);
        Set<String> carList = parkingManager.getRegistrationNumbers(Color.RED.toString());
        assertEquals(0, carList.size());

    }




    @Test
    public void testStatusOfParkingManager_withNoCar() throws ParkingException{
        List<ParkingStatusResponse> parkingManagerStatusResponseList = parkingManager.getStatus();
        assertEquals(0, parkingManagerStatusResponseList.size());


    }
}

