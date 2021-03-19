package ru.geekbrains.webui.box;
/*
простой объект коробки, куда можно складывать шарики,
вытаскивать их и перемешивать
Ошибка - нет регулятора удаления, тут counter может быть отрицательным -
надо, чтобы студенты это нашли с помощью тестов

 */
public class Box implements Extendable{
    private Integer ballsCounter;

    public Box() {
        this.ballsCounter = 0;
    }

    public Integer getBallsCounter() {
        return ballsCounter;
    }

    @Override
    public void addABall() {
        ballsCounter++;
    }

    @Override
    public void deleteABall() throws BoxIsEmptyException {
        if (ballsCounter<=0) throw new BoxIsEmptyException();
        ballsCounter--;
    }

    @Override
    public void shuffleTheBalls() {
        if (ballsCounter<=0) throw new NullPointerException();
    }

    @Override
    public Object isEmpty() {
        return ballsCounter==0;
    }
}
