
package models.channel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelatedPlaylists {

    @SerializedName("favorites")
    @Expose
    private String favorites;
    @SerializedName("uploads")
    @Expose
    private String uploads;
    @SerializedName("watchHistory")
    @Expose
    private String watchHistory;
    @SerializedName("watchLater")
    @Expose
    private String watchLater;

    public String getFavorites() {
        return favorites;
    }

    public String getUploads() {
        return uploads;
    }

    public String getWatchHistory() {
        return watchHistory;
    }

    public String getWatchLater() {
        return watchLater;
    }

}
