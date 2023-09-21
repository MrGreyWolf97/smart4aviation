package smart4aviation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
class Smart4AviationApplication {
    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(Smart4AviationApplication.class, args);
    }
}