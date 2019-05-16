
package models.channel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Statistics {

    @SerializedName("viewCount")
    @Expose
    private String viewCount;
    @SerializedName("commentCount")
    @Expose
    private String commentCount;
    @SerializedName("subscriberCount")
    @Expose
    private String subscriberCount;
    @SerializedName("hiddenSubscriberCount")
    @Expose
    private Boolean hiddenSubscriberCount;
    @SerializedName("videoCount")
    @Expose
    private String videoCount;

    public String getViewCount() {
        return viewCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public String getSubscriberCount() {
        return subscriberCount;
    }

    public Boolean getHiddenSubscriberCount() {
        return hiddenSubscriberCount;
    }

    public String getVideoCount() {
        return videoCount;
    }

}
