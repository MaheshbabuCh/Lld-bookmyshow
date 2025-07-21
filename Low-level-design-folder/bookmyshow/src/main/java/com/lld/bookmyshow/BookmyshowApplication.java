package com.lld.bookmyshow;

import com.lld.bookmyshow.controllers.UserController;
import com.lld.bookmyshow.dtos.UserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookmyshowApplication implements CommandLineRunner {

    UserController userController;

    @Autowired
    public BookmyshowApplication(UserController userController) {
        this.userController = userController;
    }

    @Override
    public void run(String... args) throws Exception {
        UserRequestDto userRequestDto = new UserRequestDto();

        userRequestDto.setEmail("maheshbabucherukuri@example.com");
        userRequestDto.setPassword("password");
        userRequestDto.setName("Mahesh Babu Cherukuri");
        userController.createUser(userRequestDto);



    }



    public static void main(String[] args) {

        SpringApplication.run(BookmyshowApplication.class, args);

        System.out.println("BookMyShow Application Started Successfully!");

    }


}
