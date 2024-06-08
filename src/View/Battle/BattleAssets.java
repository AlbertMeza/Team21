package View.Battle;

import Model.Character.Hero;
import Model.Character.Monster;
import View.FrameManager;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

/**
 * Class used to calculate placement of battle screen assets.
 * @author James
 * @version 1.0
 */
public class BattleAssets {

    /**
     * Scaled version of image
     */
    private Image scaledMonsterImage;

    /**
     * Scaled version of image
     */
    private Image scaledHeroImage;

    /**
     * scaled version of image.
     */
    private Image scaledPlatformImage;

    /**
     * Initialize the images and scale them according to the desired dimensions.
     *
     * @param theHero The hero character object.
     * @param theMonster The monster character object.
     * @param theVictory Boolean if won or not.
     */
    public void initialize(final Hero theHero, final Monster theMonster, final Boolean theVictory) {
        Image monsterImage;
        Image heroImage;
        Image platformImage;
        try {
            if (theVictory) {
                monsterImage = ImageIO.read(new File("src/Assets/Images/chest.png"));
            } else {
                System.out.println(theMonster.getName());
                monsterImage = ImageIO.read(new File("src/Assets/Images/" + theMonster.getName() + "Battle.png"));
            }
            heroImage = ImageIO.read(new File("src/Assets/Images/" + theHero.getName() + "Battle.png"));
            platformImage = ImageIO.read(new File("src/Assets/Images/battlePlatform.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Desired dimensions for images
        int desiredHeroWidth = 350;
        int desiredHeroHeight = 400;
        int desiredMonsterWidth = 250;
        int desiredMonsterHeight = 300;
        int desiredPlatformWidth = 250;
        int desiredPlatformHeight = 100;

        // Calculate scaling factors and scale images
        double scaleMonster = PlaceChars.scaleImage(desiredMonsterHeight, desiredMonsterWidth, monsterImage.getHeight(null), monsterImage.getWidth(null));
        double scaleHero = PlaceChars.scaleImage(desiredHeroHeight, desiredHeroWidth, heroImage.getHeight(null), heroImage.getWidth(null));
        double scalePlatform = PlaceChars.scaleImage(desiredPlatformHeight, desiredPlatformWidth, platformImage.getHeight(null), platformImage.getWidth(null));

        scaledMonsterImage = monsterImage.getScaledInstance(
                (int) (monsterImage.getWidth(null) * scaleMonster),
                (int) (monsterImage.getHeight(null) * scaleMonster),
                Image.SCALE_SMOOTH
        );

        scaledHeroImage = heroImage.getScaledInstance(
                (int) (heroImage.getWidth(null) * scaleHero),
                (int) (heroImage.getHeight(null) * scaleHero),
                Image.SCALE_SMOOTH
        );

        scaledPlatformImage = platformImage.getScaledInstance(
                (int) (platformImage.getWidth(null) * scalePlatform),
                (int) (platformImage.getHeight(null) * scalePlatform),
                Image.SCALE_SMOOTH
        );
    }

    /**
     * Want a scaled image?
     * @return Scaled image.
     */
    public Image getScaledMonsterImage() {
        return scaledMonsterImage;
    }

    /**
     * Want a scaled image?
     * @return Scaled image.
     */
    public Image getScaledHeroImage() {
        return scaledHeroImage;
    }

    /**
     * Want a scaled image?
     * @return Scaled image.
     */
    public Image getScaledPlatformImage() {
        return scaledPlatformImage;
    }
}
