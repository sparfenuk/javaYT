package animation;

import javafx.animation.RotateTransition;

import java.util.ArrayList;
import java.util.List;

public class RotationAnimation {
    private List<RotateTransition> anime;

    public RotationAnimation() {
        this.anime = new ArrayList<>();
    }

    public void setAnime(List<RotateTransition> anime) {
        this.anime = anime;
    }
    public void add(RotateTransition animation)
    {
        anime.add(animation);
    }
    public void play()
    {
        for (RotateTransition r:anime
             ) {
            r.getNode().setVisible(true);
            r.play();
        }
    }
    public void stop()
    {
        for (RotateTransition r:anime
        ) {
            r.getNode().setVisible(false);
            r.stop();
        }
    }
}
