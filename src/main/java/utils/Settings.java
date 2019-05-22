package utils;

import java.awt.*;
import java.io.*;

public class Settings implements Serializable {
    public static String API_DATA_KEY = "AIzaSyB1-COiU17RrgcEpjxWWohJjKiWI5Oq0aA";
    public static String CACHEPATH = "Cache/";
    //This is seconds parameter
    //by default it set to 7 days
    public static Long CACHELIFETIME = 86400l * 7;
//    public long startTime;
//    public long endTime;

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
    }



    public static void serealize (Settings settings) throws Exception{
        FileOutputStream outputStream = new FileOutputStream("Settings.set");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(settings);

        objectOutputStream.close();
    }
    public static Settings deSerealize () {
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
    public static boolean ifFileExist ()
    {
        File f = new File("Settings.set");
        if(f.exists() && !f.isDirectory()) {
           return true;
        }
        else { return false;}

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
