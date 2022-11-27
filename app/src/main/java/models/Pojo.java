package models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pojo {

        @SerializedName("label")
        @Expose
        private String label;
        @SerializedName("Rate")
        @Expose
        private String Rate;
        @SerializedName("id")
        @Expose
        private String id;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getRate() {
            return Rate;
        }

        public void setRate(String rate) {
            this.Rate = Rate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

