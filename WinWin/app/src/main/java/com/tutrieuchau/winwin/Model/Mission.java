package com.tutrieuchau.winwin.Model;

import com.tutrieuchau.winwin.Utils.Utils;

import java.util.List;

/**
 * Created by tutrieuchau on 2017/08/30.
 */

public class Mission {
    public String title;
    public String detail;
    public String reward;
    public int rewardBounds;
    public List<Alarm> alarms;
    public Utils.DEFAULT_ICON thumbnail;
    public List<Progress> taskProgress;

    public Mission(String title, String detail, String reward,
                   int rewardBounds, List<Alarm> alarms, Utils.DEFAULT_ICON thumbnail, List<Progress> taskProgress){
        this.title = title;
        this.detail = detail;
        this.reward = reward;
        this.rewardBounds = rewardBounds;
        this.alarms = alarms;
        this.thumbnail = thumbnail;
        this.taskProgress = taskProgress;
    }
    public class Alarm{
        public boolean active;
        public int time;
    }
    public class Progress{
        public String taskName;
        public int totalTime;
        public int progressTime;
    }
}
