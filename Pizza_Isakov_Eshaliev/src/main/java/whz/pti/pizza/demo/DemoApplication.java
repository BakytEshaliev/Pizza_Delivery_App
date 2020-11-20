package whz.pti.pizza.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import whz.pti.pizza.demo.service.TestService;

@SpringBootApplication
public class DemoApplication {

    // @Autowired
    // TestService testService;
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner init(){
        return (exp)->{
//            testService.test();
        };
    }
}
