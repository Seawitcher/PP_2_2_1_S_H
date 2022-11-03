package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


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

        User user1 = new User("Тимур", "Белокобыльский", "Timur1@mail.ru");
        User user2 = new User("Александр", "Костенко", "Alex2@mail.ru");
        User user3 = new User("Миша", "Булатов", "Misha3@mail.ru");
        User user4 = new User("Василина", "Дунаева", "Vasilina4@mail.ru");

        user1.setCar(car1);
        user2.setCar(car2);
        user3.setCar(car3);
        user4.setCar(car4);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

//        userService.add(new User("Тимур", "Белокобыльский", "Timur1@mail.ru"));
//        userService.add(new User("Александр", "Костенко", "Alex2@mail.ru", car2));
//        userService.add(new User("Миша", "Булатов", "Misha3@mail.ru", car3));
//        userService.add(new User("Василина", "Дунаева", "Vasilina4@mail.ru", car4));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Model = " + user.getCar().getModel());
            System.out.println("Series = " + user.getCar().getSeries());
            System.out.println();
        }

        List<User> users2 = userService.getUserWhoCarAccordingModelAndSeries("Mazda", 123);
        for (User user : users2) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }
        context.close();
    }
}
