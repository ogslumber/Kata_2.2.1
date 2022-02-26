package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);
      UserService userService = context.getBean(UserService.class);

      // Добавление пользователей.
      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Volkswagen", 1)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Volvo", 3)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Toyota", 5)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Audi", 7)));

      // Получение пользователей.
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
         System.out.println();
      }

      // Получение пользователя по модели и серии машины.
      User user = userService.get("Volkswagen", 1);
      System.out.println(user);

      context.close();
   }
}
