package com.project.aidoor.history;

public class HistoryItem {

    private String profile;
    private String time;
    private String status;

    public HistoryItem(){}

    public String getProfile()
    {
        return profile;
    }

    public void setProfile(String profile)
    {
        this.profile=profile;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time=time;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status=status;
    }
}
