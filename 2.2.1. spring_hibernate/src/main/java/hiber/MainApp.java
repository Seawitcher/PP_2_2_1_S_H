package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.CacheRequest;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("Mazda", 123);
        Car car2 = new Car("Lada", 124);
        Car car3 = new Car("Chevrolet", 125);
        Car car4 = new Car("Mitsubishi", 126);



      userService.add(new User("Тимур", "Белокобыльский", "Timur1@mail.ru", car1));
      userService.add(new User("Александр", "Костенко", "Alex2@mail.ru",car2));
      userService.add(new User("Миша", "Булатов", "Misha3@mail.ru", car3));
      userService.add(new User("Василина", "Дунаева", "Vasilina4@mail.ru", car4));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Model = " + user.getСar().getModel());
            System.out.println("Series = " + user.getСar().getSeries());
            System.out.println();
        }
     userService.getUserWhoCarAccordingModelAndSeries("Lada", 124);

        context.close();
    }
}
