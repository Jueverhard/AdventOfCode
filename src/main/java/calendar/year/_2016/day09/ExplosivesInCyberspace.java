package calendar.year._2016.day09;

import utils.Exercise;
import utils.enums.Part;
import utils.fileparser.Parser;

import java.io.IOException;
import java.time.LocalDate;

public class ExplosivesInCyberspace extends Exercise {

    public ExplosivesInCyberspace(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        // Data initialization
        String line = Parser.parseLines(this.getInputPath(testMode)).get(0);

        // Data decompression
        String decompressedData = decompressData(line);

        return print(decompressedData.length());
    }

    /**
     * @param data Data to decompress.
     * @return Decompressed data.
     */
    private String decompressData(String data) {
        String decompressedData = data;
        int currentIndex = 0;

        // While a compression marker is yet to be read
        while (0 <= (currentIndex = decompressedData.indexOf("(", currentIndex))) {
            // Extract compression marker
            int endIndex = decompressedData.indexOf(")", currentIndex);
            String[] markerParts = decompressedData.substring(currentIndex + 1, endIndex).split("x");
            int repeatSequenceSize = Integer.parseInt(markerParts[0]);
            int nbRepetitions = Integer.parseInt(markerParts[1]);

            // Identify the sequence to decompress
            String repeatSequence = decompressedData.substring(endIndex + 1, endIndex + 1 + repeatSequenceSize);

            // Effectively decompress the data regarding the current marker
            decompressedData = decompressedData.substring(0, currentIndex) +
                    repeatSequence.repeat(nbRepetitions) +
                    decompressedData.substring(endIndex + 1 + repeatSequenceSize);

            // Update the reading index accordingly
            currentIndex += nbRepetitions * repeatSequenceSize;
        }

        return decompressedData;
    }
}
