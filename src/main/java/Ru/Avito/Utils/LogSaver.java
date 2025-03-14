package Ru.Avito.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;

public class LogSaver {
    public static void logSave() {
        Date cur = new Date();
        String date = cur.toString().replace(':', '_').replace(' ', '_');
        Path sourceDir = Path.of("target/logs/test.log");
        Path targetDir = Path.of(String.format("src/main/resources/logs/LOG_%s.txt", date));

        try {
            Files.walk(sourceDir)
                    .forEach(source -> {
                        Path target = targetDir.resolve(sourceDir.relativize(source));
                        try {
                            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
