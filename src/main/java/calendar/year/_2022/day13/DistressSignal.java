package calendar.year._2022.day13;

import utils.Exercise;
import utils.enums.Part;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DistressSignal extends Exercise {

    public DistressSignal(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        return Part.PART_1.equals(part) ? exercise1(testMode) : exercise2(testMode);
    }

    private String exercise1(boolean testMode) throws IOException {
        List<Pair<Packet, Packet>> packetPairs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            List<Packet> currentPackets = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                currentPackets.add(initializePacket(line));
                if (currentPackets.size() == 2) {
                    packetPairs.add(Pair.of(currentPackets.get(0), currentPackets.get(1)));
                    currentPackets.clear();
                }
            }
        }

        int score = packetPairs.stream()
                .filter(pair -> 0 > pair.getLeft().compareTo(pair.getRight()))
                .mapToInt(pair -> packetPairs.indexOf(pair) + 1)
                .sum();
        return print(score);
    }

    private String exercise2(boolean testMode) throws IOException {
        List<Packet> currentPackets = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isBlank()) {
                    currentPackets.add(initializePacket(line));
                }
            }
        }

        // Creates divider packets
        List<Packet> dividerPackets = IntStream.of(2, 6)
                .mapToObj(SingleValue::new)
                .map(Packet::new)
                .map(Packet::new)
                .collect(Collectors.toList());

        // Adds the divider packets into the packets list
        currentPackets.addAll(dividerPackets);

        // Sorts the packets
        List<Packet> sortedPackets = currentPackets.stream()
                .sorted()
                .collect(Collectors.toList());

        int score = sortedPackets.stream()
                .filter(dividerPackets::contains)
                .mapToInt(packet -> sortedPackets.indexOf(packet) + 1)
                .reduce(1, (a, b) -> a * b);
        return print(score);
    }

    private Packet initializePacket(String line) {
        Packet rootPacket = new Packet();
        Packet currentPacket = rootPacket;
        Stack<Packet> parents = new Stack<>();
        String[] characters = line.split("");
        String currentNumber = "";
        for (int i = 1; i < characters.length; i++) {
            String character = characters[i];
            switch (character) {
                case "[":
                    // Creation of a new packet
                    Packet newPacket = new Packet();

                    // Addition of a new packet to the current one values
                    currentPacket.getValues().add(newPacket);

                    // Actualization of the current packet
                    parents.add(currentPacket);
                    currentPacket = newPacket;
                    break;
                case "]":
                    addSingleValue(currentPacket, currentNumber);
                    currentNumber = "";

                    try {
                        // Actualization of the current packet
                        currentPacket = parents.pop();
                    } catch (EmptyStackException e) {
                        // If it is the last character (i.e. closing the packet under initialization), it's alright
                        if (i != characters.length -1) {
                            throw e;
                        }
                    }
                    break;
                case ",":
                    addSingleValue(currentPacket, currentNumber);
                    currentNumber = "";
                    break;
                default:
                    currentNumber = currentNumber.concat(character);
                    break;
            }
        }
        return rootPacket;
    }

    private void addSingleValue(Packet packet, String number) {
        if (!number.isBlank()) {
            Value newValue = new SingleValue(Integer.parseInt(number));
            packet.getValues().add(newValue);
        }
    }
}
