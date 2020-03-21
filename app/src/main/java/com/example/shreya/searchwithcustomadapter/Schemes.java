package com.example.shreya.searchwithcustomadapter;

public class Schemes {

    int heading, brief, eligibility, benefits, apply, more_info;

    public Schemes(int heading, int brief, int eligibility, int benefits, int apply, int more_info) {
        this.heading = heading;
        this.brief = brief;
        this.eligibility = eligibility;
        this.benefits = benefits;
        this.apply = apply;
        this.more_info = more_info;
    }

    public Schemes(int heading, int brief) {
        this.heading = heading;
        this.brief = brief;
    }

    public Schemes(int heading) {
        this.heading = heading;
    }

    public int getHeading() {
        return heading;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    public int getBrief() {
        return brief;
    }

    public void setBrief(int brief) {
        this.brief = brief;
    }

    public int getEligibility() {
        return eligibility;
    }

    public void setEligibility(int eligibility) {
        this.eligibility = eligibility;
    }

    public int getBenefits() {
        return benefits;
    }

    public void setBenefits(int benefits) {
        this.benefits = benefits;
    }

    public int getApply() {
        return apply;
    }

    public void setApply(int apply) {
        this.apply = apply;
    }

    public int getMore_info() {
        return more_info;
    }

    public void setMore_info(int more_info) {
        this.more_info = more_info;
    }
}
