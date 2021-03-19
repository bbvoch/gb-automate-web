package ru.geekbrains.webui.box;

public interface Extendable {
    void addABall();
    void deleteABall() throws BoxIsEmptyException;
    void shuffleTheBalls();

    Object isEmpty();
}
