import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import models.video.Item;
import models.video.Video;

import java.util.List;

public class Requests {

    public static models.channel.Channel getChannel(String channelid) throws Exception{
        String url="https://www.googleapis.com/youtube/v3/channels";
        HttpResponse<String> response = Unirest.get(url)
                .queryString("key", Settings.API_DATA_KEY)
                .queryString("part", "snippet")
                .queryString("id",channelid)
                .asString();
        return new Gson().fromJson(response.getBody(), models.channel.Response.class).getItems().get(0).getSnippet();
    }

    public static models.channel.statistics.Statistics getChannelStatistics(String channelid) throws Exception{
        String url="https://www.googleapis.com/youtube/v3/channels";
        HttpResponse<String> response = Unirest.get(url)
                .queryString("key", Settings.API_DATA_KEY)
                .queryString("part", "statistics")
                .queryString("id",channelid)
                .asString();
        return new Gson().fromJson(response.getBody(), models.channel.statistics.Response.class).getItems().get(0).getStatistics();
    }

    public static List<models.video.Item> searchVideos(String query, int maxresults) throws Exception{
        String url="https://www.googleapis.com/youtube/v3/search";
        HttpResponse<String> response = Unirest.get(url)
                .queryString("key", Settings.API_DATA_KEY)
                .queryString("part", "snippet")
                .queryString("maxResults",maxresults)
                .queryString("q",query)
                .asString();
        return new Gson().fromJson(response.getBody(),models.video.Response.class).getItems();
    }
    public static List<models.channel.Item> searchChannels(String query, int maxresults) throws Exception{
        String url="https://www.googleapis.com/youtube/v3/channels";
        HttpResponse<String> response = Unirest.get(url)
                .queryString("key", Settings.API_DATA_KEY)
                .queryString("part", "snippet")
                .queryString("maxResults",maxresults)
                .queryString("forUsername",query)
                .asString();

        return new Gson().fromJson(response.getBody(),models.channel.Response.class).getItems();
    }
}