package GradedLab2.Example1;

import java.util.HashMap;
import java.util.Map;
/*
Question :
You are tasked with designing a media player application in Java. The application should support playing various types of media files such as MP3, MP4, and WAV. Additionally, it should provide a feature to display the metadata of the media files.

Adapter Pattern:
Implement an adapter to allow the media player to play WAV files using the existing MP3 player implementation.
Singleton Pattern:
Ensure that the media player application follows the Singleton pattern to guarantee that only one instance of the media player object exists throughout the application.
Facade Pattern:
Design a facade to simplify the process of accessing and controlling the media player functionalities. The facade should provide methods for playing, pausing, stopping, and getting metadata of media files.
Abstract Factory Pattern:
Implement an abstract factory to create different types of media players (e.g., BasicMediaPlayer and AdvancedMediaPlayer). Each factory should be capable of creating media players that support specific types of media files (e.g., MP3, MP4, WAV).
Your implementation should demonstrate the usage of these design patterns to achieve the required functionalities efficiently and maintainably.

Provide the Java code for your implementation along with explanations of how each design pattern is applied in the context of the media player application.


 */

// Singleton Pattern - MediaPlayerSingleton :ensures that only one instance of the media player object exists throughout the application.
class MediaPlayerSingleton {
    private static MediaPlayerSingleton instance;
    private Map<String, MediaPlayer> mediaPlayerMap;

    private MediaPlayerSingleton() {
        mediaPlayerMap = new HashMap<>();
    }

    public static synchronized MediaPlayerSingleton getInstance() {
        if (instance == null) {
            instance = new MediaPlayerSingleton();
        }
        return instance;
    }

    public MediaPlayer getMediaPlayer(String fileType) {
        if (!mediaPlayerMap.containsKey(fileType)) {
            throw new IllegalArgumentException("Unsupported media file type: " + fileType);
        }
        return mediaPlayerMap.get(fileType);
    }

    public void registerMediaPlayer(String fileType, MediaPlayer mediaPlayer) {
        mediaPlayerMap.put(fileType, mediaPlayer);
    }
}

// Abstract Factory Pattern - MediaPlayerFactory ***************************************************************
interface MediaPlayerFactory {
    MediaPlayer createMediaPlayer();
}
// Concrete Factory - BasicMediaPlayerFactory
class BasicMediaPlayerFactory implements MediaPlayerFactory {
    @Override
    public MediaPlayer createMediaPlayer() {
        return new BasicMediaPlayer();
    }
}
// Concrete Factory - AdvancedMediaPlayerFactory
class AdvancedMediaPlayerFactory implements MediaPlayerFactory {
    @Override
    public MediaPlayer createMediaPlayer() {
        return new AdvancedMediaPlayer();
    }
}
// Abstract product
interface MediaPlayer {
    void play(String fileName);
    void stop(String fileName);
    String getMetadata(String fileName);
}

// Concrete Product1
class BasicMediaPlayer implements MediaPlayer {
    @Override
    public void play(String fileName) {
        System.out.println("Playing " + fileName + " using Basic Media Player");
    }
    @Override
    public void stop(String fileName ) {
        System.out.println("Stopping playback");
    }
    @Override
    public String getMetadata(String fileName) {
        return "Metadata of " + fileName + " from Basic Media Player";
    }
}

// // Concrete Product2
class AdvancedMediaPlayer implements MediaPlayer {
    @Override
    public void play(String fileName) {
        System.out.println("Playing " + fileName + " using Advanced Media Player");
    }
    @Override
    public void stop(String fileName) {
        System.out.println("Stopping playback");
    }
    @Override
    public String getMetadata(String fileName) {
        return "Metadata of " + fileName + " from Advanced Media Player";
    }
}

//***************************************************************************************************

// Adapter Pattern - WAVPlayerAdapter
class WAVPlayer { // adaptee
    public void playWAV(String fileName) {
        System.out.println("Playing " + fileName + " using WAV Player");
    }
    public void stopWAV(String fileName) {
        System.out.println("Stopping " + fileName + " using WAV Player");
    }
    public String getWAVMetadata(String fileName) {
        return "Metadata of " + fileName + " from WAV Player";
    }
}

class WAVPlayertoMediaPlayerAdapter implements MediaPlayer { // MediaPlayer: target class
    private WAVPlayer wavPlayer;  // adaptee

    public WAVPlayertoMediaPlayerAdapter() {
        this.wavPlayer = new WAVPlayer(); //object adapter
    }
    @Override
    public void play(String fileName) {
        wavPlayer.playWAV(fileName);  // adaptee is connected to target functions
    }

    @Override
    public void stop(String fileName) {
        wavPlayer.stopWAV(fileName);
    }

    @Override
    public String getMetadata(String fileName) {
        return wavPlayer.getWAVMetadata(fileName);
    }
}
//***************************************************************************************************
// Facade Pattern - MediaPlayerFacade  -for accessing and controlling media player functionalities
class MediaPlayerFacade {
    private final MediaPlayerSingleton mediaPlayerSingleton;

    public MediaPlayerFacade() {
        this.mediaPlayerSingleton = MediaPlayerSingleton.getInstance();
    }

    public void play(String fileType, String fileName) {
        MediaPlayer mediaPlayer = mediaPlayerSingleton.getMediaPlayer(fileType);
        mediaPlayer.play(fileName);
    }

    public void stop(String fileType) {
        MediaPlayer mediaPlayer = mediaPlayerSingleton.getMediaPlayer(fileType);
        mediaPlayer.stop(fileType);
    }

    public String getMetadata(String fileType, String fileName) {
        MediaPlayer mediaPlayer = mediaPlayerSingleton.getMediaPlayer(fileType);
        return mediaPlayer.getMetadata(fileName);
    }
}
//***************************************************************************************************
public class Main2 {
    public static void main(String[] args) {
        // Register MediaPlayer implementations with Singleton
        MediaPlayerSingleton.getInstance().registerMediaPlayer("MP3", new BasicMediaPlayer());
        MediaPlayerSingleton.getInstance().registerMediaPlayer("MP4", new BasicMediaPlayer());
        MediaPlayerSingleton.getInstance().registerMediaPlayer("WAV", new WAVPlayertoMediaPlayerAdapter());

        // Use Facade to play media files
        MediaPlayerFacade mediaPlayerFacade = new MediaPlayerFacade();
        mediaPlayerFacade.play("MP3", "song.mp3");
        mediaPlayerFacade.play("WAV", "sound.wav");
        System.out.println(mediaPlayerFacade.getMetadata("MP4", "video.mp4"));
    }
}

