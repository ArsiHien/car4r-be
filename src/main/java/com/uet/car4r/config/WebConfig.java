package com.uet.car4r.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.javafaker.Faker;
import com.uet.car4r.constant.Role;
import com.uet.car4r.entity.Customer;
import com.uet.car4r.entity.User;
import com.uet.car4r.mapper.UserMapper;
import com.uet.car4r.repository.CustomerRepository;
import com.uet.car4r.repository.UserRepository;
import com.uet.car4r.service.TokenService;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

//  @Override
//  public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    registry.addResourceHandler("/**")
//        .addResourceLocations("classpath:/static/");
//  }


  /**
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Getter
  @Setter
  @AllArgsConstructor
  // FakeUser
  static class FakeUser {
    private String email;
    private String password;

  }


  /**
   * fake du lieu
   * @return CommandLineRunner
   */

  /*
  @Bean
  public CommandLineRunner seedUserData(UserMapper userMapper,
                                        CustomerRepository customerRepository,
                                        TokenService tokenService) {
    // tao faker
    Faker faker = new Faker();

    // doc file User.json
    File usersJson = new File("src/main/java/com/uet/car4r/User.json");

    // tao writer
    ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

    // tao 1 list fake user
    List<FakeUser> fakeUsersList = new ArrayList<>();

    return args -> {
      // Fake Data voi 30 records
      for (int i = 0; i < 30; i++) {
        // random role value
        int randomRole = new Random().nextInt(3);

        // fake password
        String fakePassword = faker.regexify("[A-Z]{1}[a-z]{1}\\d{1}[@#$%^&+=]{1}{4,6}");

        // fake user
        User user = User
            .builder()
            .id(faker.internet().uuid())
            .email(faker.internet().emailAddress())
            .firstName(faker.name().firstName())
            .lastName(faker.name().lastName())
            .password(fakePassword)
            .phone(faker.phoneNumber().phoneNumber())
            .role(Role.values()[randomRole])
            .username(faker.name().username())
            .avatar("https://picsum.photos/500")
            .build();

        // them fake user vao list
        fakeUsersList.add(new FakeUser(user.getEmail(), fakePassword));

        // set password encode bcrypt
        user.setPassword(bCryptPasswordEncoder.encode(fakePassword));

        if (user.getRole().equals(Role.CUSTOMER)) {
          Customer customer = userMapper.userEntityToCustomerEntity(user);

          if (i % 2 == 0) {
            customer.setAddress(faker.address().fullAddress());
            customer.setDrivingLicenseNo(faker.number().digits(10));
            customer.setIdentityCardNo(faker.number().digits(12));
          }

          customerRepository.save(customer);
        } else {
          userRepository.save(user);
        }

        // lay user da luu
        User existUser = userRepository.getUserByEmail(user.getEmail());

        // lay va save xuong database
        tokenService.getAccessAndRefreshToken(existUser);

        // Fake Review

        // Fake Booking

        // Fake payment

        // Fake Car Image

        // Fake Car Category

        // Fake Car Amenities

        // Fake Car

        // Fake Amenity
      }

      // ghi du lieu username raw password vao database
      objectWriter.writeValue(usersJson, fakeUsersList);
    };
  }
  */
}
