package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("BMW", 323)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Ford", 119)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("KIA", 22)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Honda", 452)));
      userService.add(new User("User5", "Lastname5", "user5@mail.ru", new Car("KIA", 456)));

      System.out.println(userService.getUserByCarSeriesAndModel("KIA", 456).toString());
      System.out.println();

      context.close();
   }
}