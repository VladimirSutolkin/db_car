package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javax.persistence.NoResultException;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Vladimir", "Sutolkin", "vl@gmail.com");
      User user2 = new User("Tim", "Suhoruchkin", "tim@gmail.com");
      User user3 = new User("Igor", "Shevyahov", "ig@gmail.com");
      User user4 = new User("Roman", "Pavlukov", "rom@gmail.com");

      Car car1 = new Car("Ford Mondeo", 4);
      Car car2 = new Car("Lada", 2110);
      Car car3 = new Car("Lada", 2110);
      Car car4 = new Car("Ria Ri0", 5);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));


      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());

      }


      System.out.println(userService.getUserByCar("Ford Mondeo", 4));



        try {
           User notFoundUser = userService.getUserByCar("Broom", 90);
      } catch (NoResultException e) {
           System.out.println("User not found");

      }

      context.close();
   }
}