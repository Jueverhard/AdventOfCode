package calendar.year._2016.day04;

import calendar.NoAdequateTestInputException;
import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SecurityThroughObscurity extends Exercise {

    public SecurityThroughObscurity(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        List<Room> rooms = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            while (null != (line = br.readLine())) {
                String[] nameAndChecksum = line.split("\\[");
                String checksum = nameAndChecksum[1].replace("]", "");

                int lastDashIndex = nameAndChecksum[0].lastIndexOf('-');
                String encryptedName = nameAndChecksum[0].substring(0, lastDashIndex);
                int sectorId = Integer.parseInt(nameAndChecksum[0].substring(lastDashIndex + 1));

                rooms.add(new Room(encryptedName, sectorId, checksum));
            }
        }

        List<Room> realRooms = rooms.stream()
                .filter(Room::isReal)
                .toList();

        int res;
        if (Part.PART_1 == part) {
            res = realRooms.stream()
                    .mapToInt(Room::getSectorId)
                    .sum();
        } else {
            Room northPoleRoom = realRooms.stream()
                    .filter(room -> room.getDecryptedName().contains("northpole"))
                    .findAny().orElseThrow(() -> new NoAdequateTestInputException("Aucune salle ne contient les objets du Pôle Nord"));
            res = northPoleRoom.getSectorId();
        }

        return print(res);
    }
}
