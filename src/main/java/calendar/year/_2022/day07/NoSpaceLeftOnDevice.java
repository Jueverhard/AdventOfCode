package calendar.year._2022.day07;

import utils.Exercise;
import utils.enums.Part;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class NoSpaceLeftOnDevice extends Exercise {

    public NoSpaceLeftOnDevice(LocalDate date) {
        super(date);
    }

    @Override
    public String run(Part part, boolean testMode) throws IOException {
        Directory root;

        try (BufferedReader br = new BufferedReader(new FileReader(this.getInputPath(testMode)))) {
            root = intializeFileSystem(br);
        }

        return Part.PART_1.equals(part) ? exercise1(root) : exercise2(root);
    }

    private String exercise1(Directory root) {
        List<Directory> directories = flatten(root);

        int totalSizeOfAtMost100000Files = directories.stream()
                .map(Directory::getSize)
                .filter(size -> size <= 100000)
                .reduce(0, Integer::sum);

        return print(totalSizeOfAtMost100000Files);
    }

    private String exercise2(Directory root) {
        int totalDiskSpace = 70000000;
        int freeSpaceExpected = 30000000;
        int neededSpace = freeSpaceExpected - (totalDiskSpace - root.getSize());

        List<Directory> directories = flatten(root);

        int directoryToDeleteSize = directories.stream()
                .map(Directory::getSize)
                .filter(size -> size >= neededSpace)
                .sorted()
                .findFirst()
                .orElseThrow();

        return print(directoryToDeleteSize);
    }

    private Directory intializeFileSystem(BufferedReader br) throws IOException {
        String line;

        // Initialise la racine
        String firstLine = br.readLine();
        Directory root = new Directory(firstLine.split(" ")[2]);
        Directory currentDir = root;
        Stack<Directory> parents = new Stack<>();

        // Analyse l'arborescence des fichiers
        while ((line = br.readLine()) != null) {
            String[] instructions = line.split(" ");
            switch (instructions[0]) {
                case "$":
                    currentDir = parseCmd(currentDir, parents, instructions);
                    break;
                case "dir":
                    Directory newDir = new Directory(instructions[1]);
                    currentDir.addFile(newDir);
                    break;
                default:
                    int size = Integer.parseInt(instructions[0]);
                    String name = instructions[1];
                    File newFile = new File(name, size);
                    currentDir.addFile(newFile);
                    break;
            }
        }

        return root;
    }

    private Directory parseCmd(Directory currentDir, Stack<Directory> parents, String[] instructions) {
        switch (instructions[1]) {
            case "cd":
                if ("..".equals(instructions[2])) {
                    return parents.pop();
                } else {
                    parents.add(currentDir);
                    return (Directory) currentDir.getSubFile(instructions[2]);
                }
            case "ls":
                return currentDir;
            default:
                throw new RuntimeException("Commande non prévue");
        }
    }

    private List<Directory> flatten(Directory dir) {
        // Fetch all the various-level-children
        List<Directory> directories = dir.getFiles().stream()
                .filter(Directory.class::isInstance)
                .map(file -> flatten((Directory) file))
                .flatMap(List::stream)
                .collect(Collectors.toList());

        // Add the initial directory
        directories.add(dir);

        return directories;
    }
}
