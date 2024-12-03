package com.uet.car4r.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.javafaker.Faker;
import com.uet.car4r.constant.Role;
import com.uet.car4r.entity.User;
import com.uet.car4r.repository.UserRepository;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

//  @Override
//  public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    registry.addResourceHandler("/**")
//        .addResourceLocations("classpath:/static/");
//  }

  //  private final UserRepository userRepository;
//
//  @Getter
//  @Setter
//  @AllArgsConstructor
//  // can dat static neu khong se ko load duoc class
//  static class FakeUser {
//    private String email;
//    private String password;
//
//  }
//
//  @Bean
//  public CommandLineRunner seedData() {
//    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//    Faker faker = new Faker();
//    File usersJson = new File("src/main/java/com/uet/car4r/User.json");
//    ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
//
//    List<FakeUser> fakeUsersList = new ArrayList<>();
//
//    return args -> {
//      // Fake Data
//      for (int i = 0; i < 30; i++) {
//        // random role *
//        int randomRole = new Random().nextInt(3);
//
//        String fakePassword = faker.regexify("[A-Z]{1}[a-z]{1}\\d{1}[@#$%^&+=]{1}{4,6}");
//        User user = User
//            .builder()
//            .id(faker.internet().uuid())
//            .email(faker.internet().emailAddress())
//            .firstName(faker.name().firstName())
//            .lastName(faker.name().lastName())
//            .password(fakePassword)
//            .phone(faker.phoneNumber().phoneNumber())
//            .role(Role.values()[randomRole])
//            .username(faker.name().username())
//            .avatar("https://picsum.photos/500")
//            .build();
//
//
//
//        // Fake Review
//
//        // Fake Customer
//
//        // Fake Car Image
//
//        // Fake Car Category
//
//        // Fake Car Amenities
//
//        // Fake Car
//
//        // Fake Amenity
//
//
//
//        fakeUsersList.add(new FakeUser(user.getEmail(), fakePassword));
//
//        user.setPassword(bCryptPasswordEncoder.encode(fakePassword));
//        userRepository.save(user);
//      }
//
//      objectWriter.writeValue(usersJson, fakeUsersList);
//    };
//  }

}
