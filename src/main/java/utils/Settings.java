package utils;

import java.awt.*;
import java.io.*;

public class Settings implements Serializable {
    public static String API_DATA_KEY = "AIzaSyB1-COiU17RrgcEpjxWWohJjKiWI5Oq0aA";
    //This is seconds parameter
    //by default it set to 7 days
    public static Long CACHELIFETIME = 86400l * 7;
    public  String cachePath ;
    private  Boolean isCacheSave;
    private  Boolean isLoadTimeShow;

    @Override
    public String toString() {
        return "Settings{" +
                "cachePath='" + cachePath + '\'' +
                ", isCacheSave=" + isCacheSave +
                ", isLoadTimeShow=" + isLoadTimeShow +
                '}';
    }

    public String getCachePath() {
        return cachePath+"/";
    }

    public void setCachePath(String cachePath) {
        this.cachePath = cachePath;
    }

    public Boolean getCacheSave() {
        return isCacheSave;
    }

    public void setCacheSave(Boolean cacheSave) {
        isCacheSave = cacheSave;
    }

    public Boolean getLoadTimeShow() {
        return isLoadTimeShow;
    }

    public void setLoadTimeShow(Boolean loadTimeShow) {
        isLoadTimeShow = loadTimeShow;
    }

    public Settings() {
        this.cachePath = "Cache/";
        this.isCacheSave = true;
        this.isLoadTimeShow = true;

        File file = new File("Settings.set");
        if(file.exists()) {
            Settings deSerialized = deSerialize();
            this.cachePath = deSerialized.cachePath;
            this.isCacheSave = deSerialized.isCacheSave;
            this.isLoadTimeShow = deSerialized.isLoadTimeShow;
        }
        else
        {
            try {
                file.createNewFile();
                serialize(this);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    public static void serialize (Settings settings) throws Exception{
        FileOutputStream outputStream = new FileOutputStream("Settings.set");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(settings);

        objectOutputStream.close();
    }
    public static Settings deSerialize () {
        FileInputStream fileInputStream = null;
        Settings settings;
        try {
            fileInputStream = new FileInputStream("Settings.set");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            settings = (Settings) objectInputStream.readObject();

        } catch (Exception e) {
            e.printStackTrace();
            return new Settings();
        }
        return settings;

    }
    public static boolean isFileExists ()
    {
        File f = new File("Settings.set");
        return (f.exists() && !f.isDirectory());
    }

   /* public  long getDuration() {
        return endTime - startTime;
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }
    public void stop() {
        endTime = System.currentTimeMillis();
    }*/


}
