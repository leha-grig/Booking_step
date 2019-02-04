package com.booking;


//TODO в консоли вынести методы проверки в отдельный класс?
//TODO "красивая" консоль - без свитчей, с стрим-фильтром и Enum?
//TODO переделать HashMap для Bookings - так, чтоб ключами стояли АйДи букингс?
//TODO переделать объекты - разные конструкторы для сериализации/десериализации - и сохранять в файлы построчно
//TODO переделать ДАО - так, чтоб тип объектов был интерфейс???
//TODO консоль - проводть запись в файлы только после нажатия log out?

//TODO авторизация по-Рихальскому



import com.booking.Exceptions.*;


public class Main {
    public static void main(String[] args) {

        /*UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        UserController userController = new UserController(userService);

        try {
            userController.createNewUser("Alex", "Grig", 1978, "alex-grig", "qwerty");
            userController.createNewUser("Mike", "Smith", 2017, "mike_smith", "qwerty");
            userController.createNewUser("Max", "Fry", 1980, "max_fry", "qwerty");

        } catch (PasswordFormatException | LoginFormatException | StringValidationException | UserMatchException | LoginMatchException | YearOfBirthFormatException e) {
            e.getMessage();
        }*/


        ConsoleApp console = null;
        try {
            console = new ConsoleApp();
        } catch (FileReadingException e) {
            System.out.println(e.getMessage());
        }
        console.chooseIniOption();

    }


}
