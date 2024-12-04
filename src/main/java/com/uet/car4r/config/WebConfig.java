package com.uet.car4r.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
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
