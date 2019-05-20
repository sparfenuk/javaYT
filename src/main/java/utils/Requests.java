package utils;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import models.channel.Channel;
import models.channel.Statistics;

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
                    .queryString("part", "snippet,statistics")
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
        } else {
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

    public static long getChannelCommentsCount(String channelId) throws Exception {
        int res = 0;
        models.search.Response r = Requests.getChannelVideosId(channelId, "");
        int total = r.getPageInfo().getTotalResults(), per = r.getPageInfo().getResultsPerPage()-1;
        if (total > per)
            for (int i = per; i < total; i += per)
                r.getItems().addAll(Requests.getChannelVideosId(channelId, r.getNextPageToken()).getItems());

        String ids;

        for (int j = 0; j < (total/per)+1; j++) {
            ids = "";
            for (int i = 0; i < per && ((50*j)+i) < r.getItems().size(); i++)
                    if (r.getItems().get((50*j)+i).getId().getVideoId() != null)
                        ids += r.getItems().get((50*j)+i).getId().getVideoId() + ",";

            models.video.Response resp =  getChannelVideosStatistics(ids);
            for (models.video.Item item: resp.getItems())
                try {
                    res += Integer.parseInt(item.getStatistics().getCommentCount());
                }
                catch (Exception e){
                    System.out.println("Video has no comments");
                }

        }
        //System.out.println(ids);
        return res;
    }


    private static models.search.Response getChannelVideosId(String channelid, String nextPageToken) throws Exception {
        String json = "";
        File cacheFile = new File(Settings.CACHEPATH + Objects.hash(channelid + nextPageToken) + ".json");
        if (!cacheFile.createNewFile()) {

            if (cacheFile.lastModified() + Settings.CACHELIFETIME * 1000 < System.currentTimeMillis()) {
                cacheFile.delete();
                return getChannelVideosId(channelid, nextPageToken);
            }
            json = readFile(cacheFile.getPath(), java.nio.charset.StandardCharsets.UTF_8);

        } else {
            String url = "https://www.googleapis.com/youtube/v3/search";
            HttpResponse<String> response = Unirest.get(url)
                    .queryString("key", Settings.API_DATA_KEY)
                    .queryString("part", "id")
                    .queryString("maxResults", 50)
                    .queryString("channelId", channelid)
                    .queryString("pageToken", nextPageToken)
                    .queryString("order", "date")
                    .asString();
            json = response.getBody();
            Files.write(cacheFile.toPath(), json.getBytes());
        }

        return new Gson().fromJson(json, models.search.Response.class);
    }


    private static models.video.Response getChannelVideosStatistics(String videos) throws Exception {
        String json = "";
        File cacheFile = new File(Settings.CACHEPATH + Objects.hash(videos) + ".json");
        if (!cacheFile.createNewFile()) {

            if (cacheFile.lastModified() + Settings.CACHELIFETIME * 1000 < System.currentTimeMillis()) {
                cacheFile.delete();
                return getChannelVideosStatistics(videos);
            }
            json = readFile(cacheFile.getPath(), java.nio.charset.StandardCharsets.UTF_8);

        } else {
            String url = "https://www.googleapis.com/youtube/v3/videos";
            HttpResponse<String> response = Unirest.get(url)
                    .queryString("key", Settings.API_DATA_KEY)
                    .queryString("part", "statistics")
                    .queryString("id", videos)
                    .asString();
            json = response.getBody();
            Files.write(cacheFile.toPath(), json.getBytes());
        }
        return new Gson().fromJson(json, models.video.Response.class);
    }


    private static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}