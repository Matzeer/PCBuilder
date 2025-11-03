package fr.esiea.pcbuilder.infrastructure.presentation.views;

public interface View {
    void display(String content);

    String getUserInput();

    void close();
}

