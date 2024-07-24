
package Utilz;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static Main.Game.GAME_HEIGHT;
import static Main.Game.GAME_WIDTH;

public class LoadSave {

    public static final String PLAYER_ATLAS = "Mpicture.png";
    public static final String LEVEL_ATLAS = "Level.png";
    public static final String LEVEL_ONE_DATA = "LevelData.png";
    public static final String BLACK = "black.png";


    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img1 = null;

        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);


        try {
            img1 = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img1;
    }

    public static int[][] getLevelData()
    {
        int [][] levelData = new int [14][26];
        BufferedImage img1 = LoadSave.GetSpriteAtlas(LEVEL_ONE_DATA);
        for ( int i = 0 ; i < img1.getHeight(); i++)
        {
            for (int j = 0; j < img1.getWidth() ; j++)
            {

                Color color = new Color(img1.getRGB(j,i));
                int value =  color.getRed();
                if ( value >= 48)
                {
                    value = 0;
                }
                levelData [i][j] = value;
            }
        }
        return levelData;

    }
}
