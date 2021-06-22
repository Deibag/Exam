import lt.vtmc.exam.*;
import org.junit.platform.engine.support.descriptor.FileSystemSource;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Provides method implementetion for TransportManger class
 */

public class createTransportManagerImpl implements TransportManager {

    private static final Logger logger = Logger.getLogger(createTransportManagerImpl.class.getName());

    private List<Bus> busList = new ArrayList<>();
    private List<Passenger> passengerList = new ArrayList<>();
    private Map<Bus, List<Passenger>> registeredList = new HashMap<>();


    /**
     * Creates a new bus
     * @param s bus identifier
     * @param i number of seats in the bus
     * @return registered bus instance
     */
    @Override
    public Bus createBus(String s, int i) {
        logger.info("Creating bus");
        if(i <= 0 || s.isBlank() || s == null){
            logger.warning("Cannot create bus with empty or null values");
            throw new IllegalArgumentException();
        }
        Bus bus = new Bus(s, i);
        busList.add(bus);
        return bus;
    }

    /**
     * Returns all registered buses
     * @return list of buses
     */
    @Override
    public List<Bus> getCreatedBuses() {
        logger.info("Returning list of buses");
        return busList;
    }

    /**
     * Returns a bus by its id
     * @param s bus identifier
     * @return registered bus
     */
    @Override
    public Bus getBusById(String s) {
        logger.info("Getting bus by id");
        if(s == null){
            logger.warning("Value cannot be null");
            throw new IllegalArgumentException();
        }
        for (Bus bus: busList) {
            if(bus.getId().equals(s)){
                return bus;
            }
        }
        return null;
    }

    /**
     * Creates a passenger
     * @param s passenger's name
     * @param s1 passenger's surname
     * @param i passenger's age
     * @return passenger object
     */
    @Override
    public Passenger createPassenger(String s, String s1, int i) {
        logger.info("Creating passenger");
        if(s.isEmpty() || s1.isEmpty()){
            logger.warning("Value cannot be empty");
            throw new IllegalArgumentException();
        }
        Passenger passenger = new Passenger(s, s1, i);
        passengerList.add(passenger);
        return passenger;
    }

    /**
     * Assigns a passenger to a bus.
     * @param bus a bus
     * @param i a seat number
     * @param passenger a passenger
     * @throws SeatIsOccupiedException
     */
    @Override
    public void registerPassenger(Bus bus, int i, Passenger passenger) throws SeatIsOccupiedException {
        logger.info("registering passenger");
        if(!registeredList.containsKey(bus)){
            List<Passenger> list = new ArrayList<>();
            passenger.setSeatNo(i);
            list.add(passenger);
            registeredList.put(bus, list);
        } else {
            List<Passenger> list = registeredList.get(bus);
            for (Passenger pass: list) {
                if(pass.getSeatNo() == i){
                    logger.info("Seat is occupied");
                    throw new SeatIsOccupiedException();
                }
            }
            passenger.setSeatNo(i);
            list.add(passenger);
            registeredList.put(bus, list);
        }

    }

    /**
     * Returns all passengers assigned to the bus
     * @param s a bus identifier
     * @return a list of passengers
     */
    @Override
    public List<Passenger> getPassengers(String s) {
        logger.info("Getting list of passenger on a bus");
        Bus busReturn = null;
        for (Bus bus: busList) {
            if(bus.getId().equals(s)){
                busReturn = bus;
            }
        }
        return registeredList.get(busReturn);
    }

    /**
     * Returns the oldest passenger on the bus or null if no passengers exist
     * @param s bus idenifier
     * @return the oldest passenger
     */
    @Override
    public Passenger getOldestPassenger(String s) {
        logger.info("Getting oldest passenger on a bus");
        Bus busReturn = null;
        for (Bus bus: busList) {
            if(bus.getId().equals(s)){
                busReturn = bus;
            }
        }
        List<Passenger> list = registeredList.get(busReturn);
        Passenger passenger = list.get(0);
        for (Passenger pass: list) {
            if(pass.getAge() > passenger.getAge()){
                passenger = pass;
            }
        }
        return passenger;
    }

    /**
     * Returns the average passengers' age on the bus
     * @param s bus identifier
     * @return the average passenger's age
     */
    @Override
    public double getAveragePassengerAge(String s) {
        logger.info("Getting average age of passenger's on a bus");
        Bus busReturn = null;
        for (Bus bus: busList) {
            if(bus.getId().equals(s)){
                busReturn = bus;
            }
        }
        List<Passenger> list = registeredList.get(busReturn);
        return list.stream().mapToInt(p -> p.getAge()).average().getAsDouble();
    }

    /**
     * Order all passengers on the bus alphabetically by surname and then name.
     * I.e., if surnames differ, then order by them, if not, then order by name.
     * @param s bus identifier
     * @return ordered Collection if passengers
     */
    @Override
    public Collection<Passenger> getOrderedPassengers(String s) {
        logger.info("Ordering passengers on a bus by surname and name");
        Bus busReturn = null;
        for (Bus bus: busList) {
            if(bus.getId().equals(s)){
                busReturn = bus;
            }
        }
        List<Passenger> list = registeredList.get(busReturn);
        List<Passenger> sorted = list.stream().sorted(Comparator.comparing(Passenger::getSurname)).collect(Collectors.toList());

        return sorted;
    }

    /**
     * Returns a list of passengers on the bus that matches a given predicate
     * @param s bus indentifier
     * @param passengerPredicate search predicate
     * @return list of passengers that this predicate matches
     */
    @Override
    public List<Passenger> findPassengersBy(String s, PassengerPredicate passengerPredicate) {
        logger.info("Gettinh passenger by id and predicate");
        Bus busReturn = null;
        for (Bus bus: busList) {
            if(bus.getId().equals(s)){
                busReturn = bus;
            }
        }
        List<Passenger> list = registeredList.get(busReturn);
        return list.stream().filter(passengerPredicate::test).collect(Collectors.toList());
    }
}
