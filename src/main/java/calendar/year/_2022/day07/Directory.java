package calendar.year._2022.day07;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Directory extends File {

    private List<File> files = new ArrayList<>();

    public Directory(String name) {
        super(name, 0);
    }

    public void addFile(File file) {
        this.files.add(file);
    }

    public File getSubFile(String name) {
        return files.stream()
                .filter(file -> name.equals(file.getName()))
                .findFirst().orElseThrow();
    }

    @Override
    public int getSize() {
        return files.stream()
                .mapToInt(File::getSize)
                .sum();
    }
}
