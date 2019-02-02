package com.booking;

//TODO продвинутое ДЗ
//TODO добавить в консоль возможность выхода в мэйн меню из любой точки?
//TODO в консоли вынести методы проверки в отдельный класс?
//TODO "красивая" консоль - без свитчей, с стрим-фильтром и Enum?
//TODO переделать HashMap для Bookings - так, чтоб ключами стояли АйДи букингс? Идея в оптимизации поиска.


public class Main {
    public static void main(String[] args) {
        ConsoleApp console = new ConsoleApp();
        console.chooseIniOption();
    }
}
