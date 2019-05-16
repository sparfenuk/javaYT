
package models.channel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import models.shared.Localized;
import models.shared.Thumbnails;

public class Info {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("customUrl")
    @Expose
    private String customUrl;
    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;
    @SerializedName("thumbnails")
    @Expose
    private Thumbnails thumbnails;
    @SerializedName("localized")
    @Expose
    private Localized localized;
    @SerializedName("country")
    @Expose
    private String country;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCustomUrl() {
        return customUrl;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    public Localized getLocalized() {
        return localized;
    }

    public String getCountry() {
        return country;
    }

}
