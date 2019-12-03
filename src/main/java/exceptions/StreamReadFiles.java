package exceptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;


public class StreamReadFiles {
    public static Optional<Stream<String>> getLines(String fn) {
        try {
            return Optional.of(Files.lines(Paths.get(fn)));
        } catch (IOException e) {
            return Optional.empty();
        }
    }
//    public static Stream<String> getLines(String fn) {
//        try {
//            return Files.lines(Paths.get(fn));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public static void main(String[] args) {
        Stream.of("a.txt", "hide-b.txt", "c.txt")
//                .flatMap(fn -> getLines(fn))
                .map(fn -> getLines(fn))
                .peek(opt -> {
                    if (!opt.isPresent()) {
                        System.out.println("Ooops");
                    }
                })
                .filter(opt -> opt.isPresent())
                .flatMap(opt -> opt.get())
                .forEach(s -> System.out.println(s));
    }
}
