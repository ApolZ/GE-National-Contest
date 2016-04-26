package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Created by AZ on 2016/4/26.
 */

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }
}
