package example.org.utils;

import java.awt.*;

public class ScreenManager
{
    private final int screenWidth;
    private final int screenHeight;


    public ScreenManager() {
        // Pobieramy wymiary ekranu
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        this.screenWidth = (int) size.getWidth();
        this.screenHeight = (int) size.getHeight();
    }

    public Point getCenterPosition (int windowWidth, int windowHeight)
    {
        //screenWidth/2 - srodek x
        //screenHeight/2 - srodek y
        // punk bedzie dokladnie w srodku co oznacza ze rog okna bedzie w srodku
        // chcemy przeniesc o polowe w lewo i polowe do gory

        int pos1 = (screenWidth - windowWidth)/2;
        int pos2 = (screenHeight - windowHeight)/2;

        return new Point(pos1,pos2);
    }

}
