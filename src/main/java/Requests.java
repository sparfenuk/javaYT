import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import models.channel.Channel;
import models.search.Item;

import java.util.List;

public class Requests {

    public static Channel getChannel(String channelid) throws Exception {
        String url = "https://www.googleapis.com/youtube/v3/channels";
        HttpResponse<String> response = Unirest.get(url)
                .queryString("key", Settings.API_DATA_KEY)
                .queryString("part", "snippet")
                .queryString("id", channelid)
                .asString();
        return new Gson().fromJson(response.getBody(), models.channel.Response.class).getItems().get(0);
    }

    //types
    //channel,playlist,video
    public static List<models.search.Item> search(String query, String type, int maxresults) throws Exception {
        String url = "https://www.googleapis.com/youtube/v3/search";
        HttpResponse<String> response = Unirest.get(url)
                .queryString("key", Settings.API_DATA_KEY)
                .queryString("part", "snippet")
                .queryString("type", type.toLowerCase())
                .queryString("maxResults", maxresults)
                .queryString("q", query)
                .asString();
        return new Gson().fromJson(response.getBody(), models.search.Response.class).getItems();
    }
}