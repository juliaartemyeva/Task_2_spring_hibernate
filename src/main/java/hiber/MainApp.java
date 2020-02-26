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

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car(123, "Audi")));
        userService.add(new User("a", "a", "user2@mail.ru", new Car(123, "Audi")));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car(11, "Audi")));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car(124, "Audi")));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }
        if (userService.ifUserExists("a", "a", "user2@mail.ru")) {
            System.out.println(userService.getUserByNamesAndEmail("a", "a", "user2@mail.ru"));
        }
        userService.deleteUser("User4", "Lastname4", "user4@mail.ru");
        userService.add(new User("Julia", "Artem", "ggg", new Car(777, "RangeRover")));
        System.out.println(userService.getUserByCar("RangeRover", 777));
        context.close();
    }
}
