package fr.esiea.pcbuilder.infrastructure.presentation.views;

import java.util.Scanner;

public class ConsoleView implements View {
    private final Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String getUserInput() {
        return scanner.nextLine();
    }

    @Override
    public void display(String content) {
        System.out.println(content);
    }

    @Override
    public void close() {
        scanner.close();
    }
}
