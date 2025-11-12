package fr.esiea.pcbuilder.presentation.views;

public interface View {
    void display(String content);

    String getUserInput();

    void close();
}

