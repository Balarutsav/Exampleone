
package com.example.dhruvil.spit_it_out.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Spit {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("dt_create")
    @Expose
    private String dtCreate;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("preview_path")
    @Expose
    private String previewPath;
    @SerializedName("blurred_path")
    @Expose
    private String blurredPath;
    @SerializedName("signature_path")
    @Expose
    private String signaturePath;
    @SerializedName("posterity")
    @Expose
    private String posterity;
    @SerializedName("spiting_id")
    @Expose
    private Object spitingId;
    @SerializedName("dt_seen")
    @Expose
    private Object dtSeen;
    @SerializedName("cropped_picture_path")
    @Expose
    private String croppedPicturePath;
    @SerializedName("seen_state")
    @Expose
    private Object seenState;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("views")
    @Expose
    private String views;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("desctype")
    @Expose
    private String desctype;
    @SerializedName("sharetext")
    @Expose
    private String sharetext;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(String dtCreate) {
        this.dtCreate = dtCreate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPreviewPath() {
        return previewPath;
    }

    public void setPreviewPath(String previewPath) {
        this.previewPath = previewPath;
    }

    public String getBlurredPath() {
        return blurredPath;
    }

    public void setBlurredPath(String blurredPath) {
        this.blurredPath = blurredPath;
    }

    public String getSignaturePath() {
        return signaturePath;
    }

    public void setSignaturePath(String signaturePath) {
        this.signaturePath = signaturePath;
    }

    public String getPosterity() {
        return posterity;
    }

    public void setPosterity(String posterity) {
        this.posterity = posterity;
    }

    public Object getSpitingId() {
        return spitingId;
    }

    public void setSpitingId(Object spitingId) {
        this.spitingId = spitingId;
    }

    public Object getDtSeen() {
        return dtSeen;
    }

    public void setDtSeen(Object dtSeen) {
        this.dtSeen = dtSeen;
    }

    public String getCroppedPicturePath() {
        return croppedPicturePath;
    }

    public void setCroppedPicturePath(String croppedPicturePath) {
        this.croppedPicturePath = croppedPicturePath;
    }

    public Object getSeenState() {
        return seenState;
    }

    public void setSeenState(Object seenState) {
        this.seenState = seenState;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesctype() {
        return desctype;
    }

    public void setDesctype(String desctype) {
        this.desctype = desctype;
    }

    public String getSharetext() {
        return sharetext;
    }

    public void setSharetext(String sharetext) {
        this.sharetext = sharetext;
    }

}
