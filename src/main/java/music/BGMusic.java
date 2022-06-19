package music;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BGMusic {
    private AdvancedPlayer player;
    boolean loop = true;
    private File file;

    public BGMusic(String filename) throws FileNotFoundException, JavaLayerException {
        System.out.println("music is on！");
        file = new File(filename);
    }

    public void circularPlay() {
        Thread currentThread;
        // continuously run in new thread to play in background
        currentThread = new Thread() {
            @Override
            public void run() {
                try {
                    do {
                        playOnce();
                    } while (loop);
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        };
        currentThread.start();
    }

    public void play() {
        Thread currentThread;
        // run in new thread to play in background
        currentThread = new Thread() {
            @Override
            public void run() {
                try {
                    playOnce();
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        };
        currentThread.start();
    }

    private void playOnce() throws FileNotFoundException, JavaLayerException {
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        player = new AdvancedPlayer(bis);
        player.play();
    }

    public void close() {
        System.out.println("music closeQQ");
        loop = false;
        player.close();
    }
}
