package com.booking.consoleApp;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Command> commands = Commands.all();
        CmdHelp help = new CmdHelp();
        help.doCommand();
        System.out.println("Print what do you want to do!");
        Scanner in = new Scanner(System.in);

        Optional<Command> cmd;
        do{
            String line = in.nextLine().trim();
            cmd = commands
                    .stream().filter(command->command.text().equalsIgnoreCase(line))
                    .findFirst();
            cmd.ifPresent(Command::doCommand);
            System.out.println("> "+cmd.get().text());
            help.doCommand();
        } while(!cmd.get().isExit());
        System.out.println("Good luck!");
    }
}
