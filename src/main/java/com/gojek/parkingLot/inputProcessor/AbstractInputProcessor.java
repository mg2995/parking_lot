package com.gojek.parkingLot.inputProcessor;


import com.gojek.parkingLot.constants.Commands;
import com.gojek.parkingLot.exceptions.ErrorCodes;
import com.gojek.parkingLot.exceptions.ParkingException;
import com.gojek.parkingLot.models.Vehicle;
import com.gojek.parkingLot.models.VehicleFactory;
import com.gojek.parkingLot.models.VehicleType;
import com.gojek.parkingLot.print.*;
import com.gojek.parkingLot.resource.ParkingManagerImp;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public abstract class AbstractInputProcessor implements Processor {

    private ParkingManagerImp parkingManagerImp;
    private VehicleFactory vehicleFactory;
    private PrintContext pc;

    public AbstractInputProcessor(VehicleFactory vehicleFactory) {
        this.vehicleFactory = vehicleFactory;
    }

    @Override
    public void executeCmd(InputParam inputCmd, Commands action) throws ParkingException {
        if (!action.equals(Commands.CREATE_PARKING_LOT) && parkingManagerImp == null) {
            throw new ParkingException(ErrorCodes.PARKING_LOT_NOT_EXIST.getMsg());
        }

        switch (action) {
            case CREATE_PARKING_LOT:
                if (parkingManagerImp != null) {
                    throw new ParkingException(ErrorCodes.PARKING_LOT_ALREADY_PRESENT.getMsg());
                }
                int capacity = Integer.parseInt(inputCmd.getCommand()[1]);
                parkingManagerImp = ParkingManagerImp.getParkingInstance(capacity);
                System.out.println("Created a parking lot with " + capacity + " slots");
                break;
            case PARK:
                Vehicle vehicle = vehicleFactory.buildVehicle(inputCmd.getCommand()[1], inputCmd.getCommand()[2], VehicleType.CAR);
                pc = new PrintContext(new PrintPark());
                pc.print(parkingManagerImp.park(vehicle));
                break;
            case LEAVE:
                int slotNumber = Integer.parseInt(inputCmd.getCommand()[1]);
                pc = new PrintContext(new PrintLeave());
                pc.print(parkingManagerImp.leave(slotNumber) ? slotNumber : -1);
                break;
            case STATUS:
                pc = new PrintContext(new PrintStatus());
                pc.print(parkingManagerImp.getStatus());
                break;
            case REGISTRATION_GIVEN_COLOR:
                Set<String> data = parkingManagerImp.getRegistrationNumbers(inputCmd.getCommand()[1]);
                pc = new PrintContext(new PrintRegNoGivenColor());
                pc.print(data);
                break;
            case SLOTS_GIVEN_COLOR:
                List<Integer> slotNumbers = parkingManagerImp.getSlotsWithColor(inputCmd.getCommand()[1]);
                pc = new PrintContext(new PrintSlotsForAColor());
                pc.print(slotNumbers);
                break;
            case SLOT_GIVEN_REGISTRATION:
                pc = new PrintContext(new PrintSlotForRegist());
                pc.print(parkingManagerImp.getCarSlot(inputCmd.getCommand()[1]));
                break;
            case EXIT:
                System.exit(0);
            default:
                System.out.println("There is no command provided");
                break;
        }
    }

    @Override
    public Optional<Commands> validator(InputParam parameters) throws ParkingException {

        Optional<Commands> commandEnumsOptional;
        if (parameters.getCommand()[0].equals("")) {
            throw new ParkingException(ErrorCodes.NO_COMMAND.getMsg());
        }
        try {
            commandEnumsOptional = Commands.findByCmd(parameters.getCommand()[0]);
            return commandEnumsOptional;
        } catch (IllegalArgumentException e) {
            throw new ParkingException(ErrorCodes.INVALID_COMMAND.getMsg());
        }
    }

    @Override
    public InputParam splitInput(String input) {
        InputParam inputParam = new InputParam();
        inputParam.setCommand(input.split(" "));
        return inputParam;
    }

    public abstract void process() throws IOException, ParkingException;
}


