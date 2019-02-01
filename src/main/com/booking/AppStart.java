package com.booking;

//TODO проверки корректности файлов и их наличия - список городов, флайтов, букингов - как это сделать?
//TODO продвинутое ДЗ
//TODO добавить в консоль возможность выхода в мэйн меню из любой точки?
//TODO в консоли вынести методы проверки в отдельный класс?
//TODO в консоли вынести эксепшены в отдельный класс?
//TODO "красивая" консоль - без свитчей, с стрим-фильтром и Enum?



public class AppStart {
    public static void main(String[] args) {
        Application console = new Application();
        console.chooseCommand();
      /* Map<String, Flight> flights = new CollectionGenerator().generateNewFlightsCollection(10,10);

    FlightPrintable ttp = new FlightPrintable(flights);
        ttp.print();*/
    }


}
