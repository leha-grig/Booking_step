package com.booking.consoleApp;

import com.booking.Exceptions.FileReadingException;
import com.booking.consoleApp.avtorization.Auth;
import com.booking.consoleApp.log.ConsoleLogger;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileReadingException {
        Auth a = new Auth();
        ConsoleLogger log = new ConsoleLogger();
        Storage storage = null;
        List<Command> commands = Commands.all(log, storage, a);
        CmdHelp help = new CmdHelp(log, storage, a);
        help.doCommand();
        System.out.println("Print what do you want to do!");
        Scanner in = new Scanner(System.in);

        Optional<Command> cmd;
        do{
            String line = in.nextLine().trim();
            cmd = commands
                    .stream().filter(command->command.text().equalsIgnoreCase(line))
                    .findFirst();
            cmd.ifPresent(command -> {
                try {
                    command.doCommand();
                } catch (FileReadingException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("> "+cmd.get().text());
            help.doCommand();
        } while(!cmd.get().isExit());
        System.out.println("Good luck!");
    }
}
