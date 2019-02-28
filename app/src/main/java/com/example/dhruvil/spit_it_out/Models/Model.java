
package com.example.dhruvil.spit_it_out.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("spits")
    @Expose
    private List<Spit> spits = null;
    @SerializedName("total")
    @Expose
    private Integer total;

    public List<Spit> getSpits() {
        return spits;
    }

    public void setSpits(List<Spit> spits) {
        this.spits = spits;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
