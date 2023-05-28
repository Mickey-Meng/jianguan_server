package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.core.domain.model.Head;

import java.util.*;

public class ProduceRecord {

    private int conponentid;
    private String conponentcode;
    private String conponenttypename;
    private String projectcode;
    private String projectname;

    private String one;//   0_0     89_0     89_1    89_2
    private String two;
    private String three;
    private String four;
    private String five;

    private Date sttime;

    private Date oneTime;
    private Date twoTime;
    private Date threeTime;
    private Date fourTime;
    private Date fiveTime;

    private Date maxTime;

    public List<ProduceRecordDetail> getProduceRecordDetails() {
        return produceRecordDetails;
    }

    public void setProduceRecordDetails(List<ProduceRecordDetail> produceRecordDetails) {
        this.produceRecordDetails = produceRecordDetails;
    }

    //yangaogao 20230527
    List<ProduceRecordDetail> produceRecordDetails ;

    public static List<Head> toMap(){

        List<Head> list=new ArrayList<>();
        Map header = new HashMap();
        list.add(new Head("构件id","conponentid",1));
        list.add(new Head("构件编码","conponentcode",2));
        list.add(new Head("构件类型名称","conponenttypename",3));
        list.add(new Head("项目名称","projectname",4));
        list.add(new Head("项目编码","projectcode",5));

        //
        return list;
    }

    public int getConponentid() {
        return conponentid;
    }

    public void setConponentid(int conponentid) {
        this.conponentid = conponentid;
    }



    public String getConponentcode() {
        return conponentcode;
    }

    public void setConponentcode(String conponentcode) {
        this.conponentcode = conponentcode;
    }

    public String getConponenttypename() {
        return conponenttypename;
    }

    public void setConponenttypename(String conponenttypename) {
        this.conponenttypename = conponenttypename;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four;
    }

    public String getFive() {
        return five;
    }

    public void setFive(String five) {
        this.five = five;
    }

    public Date getSttime() {
        return sttime;
    }

    public void setSttime(Date sttime) {
        this.sttime = sttime;
    }

    public Date getOneTime() {
        return oneTime;
    }

    public void setOneTime(Date oneTime) {
        this.oneTime = oneTime;
    }

    public Date getTwoTime() {
        return twoTime;
    }

    public void setTwoTime(Date twoTime) {
        this.twoTime = twoTime;
    }

    public Date getThreeTime() {
        return threeTime;
    }

    public void setThreeTime(Date threeTime) {
        this.threeTime = threeTime;
    }

    public Date getFourTime() {
        return fourTime;
    }

    public void setFourTime(Date fourTime) {
        this.fourTime = fourTime;
    }

    public Date getFiveTime() {
        return fiveTime;
    }

    public void setFiveTime(Date fiveTime) {
        this.fiveTime = fiveTime;
    }

    public Date getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Date maxTime) {
        this.maxTime = maxTime;
    }
}
