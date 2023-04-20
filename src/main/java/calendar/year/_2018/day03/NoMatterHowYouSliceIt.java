package calendar.year._2018.day03;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoMatterHowYouSliceIt extends Exercise {
    public NoMatterHowYouSliceIt(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        TissueClaims tissueClaims = new TissueClaims();
        final Pattern numberPattern = Pattern.compile("\\d+");
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            while (null != (line = br.readLine())) {
                Matcher matcher = numberPattern.matcher(line);
                List<Integer> inputDataList = new ArrayList<>();
                while (matcher.find()) {
                    inputDataList.add(Integer.parseInt(matcher.group()));
                }
                Claim claim = new Claim(
                        inputDataList.get(0),
                        new Position(inputDataList.get(1), inputDataList.get(2)),
                        inputDataList.get(3),
                        inputDataList.get(4)
                );
                tissueClaims.addClaim(claim);
            }
        }

        return Part.PART_1.equals(part) ? print(tissueClaims.countNbDisputedParts()) : print(tissueClaims.getUndisputedClaim().getId());
    }
}
