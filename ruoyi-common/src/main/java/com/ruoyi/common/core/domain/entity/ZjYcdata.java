package com.ruoyi.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 *
 */
public class ZjYcdata implements Serializable {
    private String id;

    private String code;

    private String name;

    private String address;

    private String lon;

    private String lat;

    private Float pm10;

    private Float pm25;

    private Float noise;

    private String tem;

    private String hum;

    private Integer pm25level;

    private Integer pm10level;

    private Integer noiselevel;

    private Integer groupid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNoiselevel() {
        return noiselevel;
    }

    public void setNoiselevel(Integer noiselevel) {
        this.noiselevel = noiselevel;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public void setNoiselevel(Float noise) {
        if(noise<40){
            this.noiselevel=1;
        }else if(noise<60||noise==40){
            this.noiselevel=2;
        }else if(noise<70||noise==60){
            this.noiselevel=3;
        }else if(noise<80||noise==70){
            this.noiselevel=4;
        }else if(noise<90||noise==90){
            this.noiselevel=5;
        }else {
            this.noiselevel=6;
        }
    }


    public void setPm25level(Integer pm25level) {
        this.pm25level = pm25level;
    }

    public Integer getPm10level() {
        return pm10level;
    }

    public void setPm10level(Integer pm10level) {
        this.pm10level = pm10level;
    }

    public Integer getPm25level() {
        return pm25level;
    }

    public void setPm10level(Float pm10) {
        if(pm10<50f){
            this.pm10level=1;
        }else if(pm10<100f||pm10==50f){
            this.pm10level=2;
        }else if(pm10<150f||pm10==100f){
            this.pm10level=3;
        }else if(pm10<200f||pm10==150f){
            this.pm10level=4;
        }else if(pm10<300f||pm10==200f){
            this.pm10level=5;
        }else{
            this.pm10level = 6;
        }
    }





    public void setPm25level(Float pm25) {
        if(pm25<50f){
            this.pm25level=1;
        }else if(pm25<100f||pm25==100f){
            this.pm25level=2;
        }else if(pm25<150f||pm25==150f){
            this.pm25level=3;
        }else if(pm25<200f||pm25==200f){
            this.pm25level=4;
        }else if(pm25<300f||pm25==300f){
            this.pm25level=5;
        }else{
            this.pm25level=6;
        }
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date time;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Float getPm10() {
        return pm10;
    }

    public void setPm10(Float pm10) {
        this.pm10 = pm10;
    }

    public Float getPm25() {
        return pm25;
    }

    public void setPm25(Float pm25) {
        this.pm25 = pm25;
    }

    public Float getNoise() {
        return noise;
    }

    public void setNoise(Float noise) {
        this.noise = noise;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ZjYcdata other = (ZjYcdata) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getLon() == null ? other.getLon() == null : this.getLon().equals(other.getLon()))
            && (this.getLat() == null ? other.getLat() == null : this.getLat().equals(other.getLat()))
            && (this.getPm10() == null ? other.getPm10() == null : this.getPm10().equals(other.getPm10()))
            && (this.getPm25() == null ? other.getPm25() == null : this.getPm25().equals(other.getPm25()))
            && (this.getNoise() == null ? other.getNoise() == null : this.getNoise().equals(other.getNoise()))
            && (this.getTem() == null ? other.getTem() == null : this.getTem().equals(other.getTem()))
            && (this.getHum() == null ? other.getHum() == null : this.getHum().equals(other.getHum()))
            && (this.getGroupid() == null ? other.getGroupid() == null : this.getGroupid().equals(other.getGroupid()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getLon() == null) ? 0 : getLon().hashCode());
        result = prime * result + ((getLat() == null) ? 0 : getLat().hashCode());
        result = prime * result + ((getPm10() == null) ? 0 : getPm10().hashCode());
        result = prime * result + ((getPm25() == null) ? 0 : getPm25().hashCode());
        result = prime * result + ((getNoise() == null) ? 0 : getNoise().hashCode());
        result = prime * result + ((getTem() == null) ? 0 : getTem().hashCode());
        result = prime * result + ((getHum() == null) ? 0 : getHum().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getGroupid() == null) ? 0 : getGroupid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", address=").append(address);
        sb.append(", lon=").append(lon);
        sb.append(", lat=").append(lat);
        sb.append(", pm10=").append(pm10);
        sb.append(", pm25=").append(pm25);
        sb.append(", noise=").append(noise);
        sb.append(", tem=").append(tem);
        sb.append(", hum=").append(hum);
        sb.append(", time=").append(time);
        sb.append(", groupid=").append(groupid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
