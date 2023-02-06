package calendar.year._2022.day15;

import utils.fileparser.Parser;
import utils.Exercise;
import calendar.year.enums.Part;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class BeaconExclusionZone extends Exercise {

    public BeaconExclusionZone(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        // Initialize the zone
        Zone zone = new Zone(Parser.parseLines(this.getInputPath(testMode)));

        if (Part.PART_1.equals(part)) {
            List<Position> noBeaconSpots = zone.defineNoBeaconSpots(2000000);
            return print(noBeaconSpots.size());
        } else {
            long minCoordinate = 0;
            long maxCoordinate = 20;
            Position distressBeacon = zone.defineDistressBeaconSpot(minCoordinate, maxCoordinate);
            return print(distressBeacon.getX() * 4000000 + distressBeacon.getY());
        }
    }
}
