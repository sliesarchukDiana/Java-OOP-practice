package org.example;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
public class Main {
    static void main() {
        log.info("Hello!!!");
        log.error("This is an error!");
    }
}