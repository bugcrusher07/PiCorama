package Main;
import TakingInput.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import static Utilz.Constants.Player_Constants.*;
import static Utilz.Constants.Direction.*;
import static Main.Game.GAME_WIDTH ;
import static Main.Game.GAME_HEIGHT;


public class GamePanel extends JPanel {


    private MouseInput mouseInput;
    private Game game;

    public GamePanel(Game game)
    {
        this.game = game;
        setPanelSize();
        mouseInput= new MouseInput(this);
        addMouseMotionListener(mouseInput);
        addMouseListener(mouseInput);
        addKeyListener(new KeyboardInput(this));
    }

    public void setPanelSize()
    {
        Dimension size = new Dimension (GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
    }

    public void updateGame()
    {

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        game.render(g);

    }

    public Game getGame()
    {
        return game;
    }


}
