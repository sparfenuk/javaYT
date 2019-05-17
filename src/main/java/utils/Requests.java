package utils;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import models.channel.Channel;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class Requests {

    public static Channel getChannel(String channelid) throws Exception {
        String json = "";
        File cacheFile = new File(Settings.CACHEPATH + Objects.hash(channelid) + ".json");
        if (!cacheFile.createNewFile()) {

            if (cacheFile.lastModified() + Settings.CACHELIFETIME * 1000 < System.currentTimeMillis()) {
                cacheFile.delete();
                return getChannel(channelid);
            }
            json = readFile(cacheFile.getPath(), java.nio.charset.StandardCharsets.UTF_8);

        } else {
            String url = "https://www.googleapis.com/youtube/v3/channels";
            HttpResponse<String> response = Unirest.get(url)
                    .queryString("key", Settings.API_DATA_KEY)
                    .queryString("part", "snippet")
                    .queryString("id", channelid)
                    .asString();
            json = response.getBody();
            Files.write(cacheFile.toPath(), json.getBytes());
        }
        return new Gson().fromJson(json, models.channel.Response.class).getItems().get(0);
    }

    //types
    //channel,playlist,video
    public static List<models.search.Item> search(String query, String type, int maxresults) throws Exception {
        String json = "";
        File cacheFile = new File(Settings.CACHEPATH + Objects.hash(query, type, maxresults) + ".json");
        if (!cacheFile.createNewFile()) {
            System.out.println(cacheFile.lastModified());
            if (cacheFile.lastModified() + Settings.CACHELIFETIME * 1000 < System.currentTimeMillis()) {
                cacheFile.delete();
                return search(query, type, maxresults);
            }
            json = readFile(cacheFile.getPath(), java.nio.charset.StandardCharsets.UTF_8);
        }
        else {
            String url = "https://www.googleapis.com/youtube/v3/search";
            HttpResponse<String> response = Unirest.get(url)
                    .queryString("key", Settings.API_DATA_KEY)
                    .queryString("part", "snippet")
                    .queryString("type", type.toLowerCase())
                    .queryString("maxResults", maxresults)
                    .queryString("q", query)
                    .asString();
            json = response.getBody();
            Files.write(cacheFile.toPath(), json.getBytes());
        }
        return new Gson().fromJson(json, models.search.Response.class).getItems();
    }
    private static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}