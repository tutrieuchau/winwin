package com.tutrieuchau.winwin.Utils;

import com.tutrieuchau.winwin.Model.Reminder;
import com.tutrieuchau.winwin.R;

/**
 * Created by ductam on 2017/08/30.
 */

public class Utils {
    public static final String[] DEFAULT_THUMBNAIL_LIST_TITLE = {"Work","Train","Meals","Sleep","Learning","Sport","Read Book","Rest", "Go Out With Friends","Social","Watch TV","Read Newspaper","Yoga","Listen Music","Baby","Take A Bath","Home Work","Chat Chit","ped","Other"};
    public static final int[] DEFAULT_THUMBNAIL_LIST_ICON = {R.drawable.ic_work2,R.drawable.ic_train,R.drawable.ic_meals,R.drawable.ic_sleep2,R.drawable.ic_learning,R.drawable.ic_swim,R.drawable.ic_book,R.drawable.ic_drink,R.drawable.ic_streetview,R.drawable.ic_facebook,R.drawable.ic_tv,R.drawable.ic_book2,R.drawable.ic_accessibility,R.drawable.ic_ipod,R.drawable.ic_child,R.drawable.ic_dot,R.drawable.ic_work,R.drawable.ic_chat1,R.drawable.ic_ped};
    public static final int[] DEFAULT_THUMBNAIL_LIST_COLOR = {R.color.olivedrab,R.color.slateblue,R.color.dimgray,R.color.indigo,R.color.darkslateblue,R.color.royalblue,R.color.darkslategray,R.color.darkgreen,R.color.themeLight,R.color.colorPrimary,R.color.yellow,R.color.aliceblue,R.color.slategray,R.color.darkred,R.color.darkolivegreen,R.color.salmon,R.color.purple,R.color.blueviolet,R.color.darkred,R.color.chartreuse,R.color.salmon,R.color.darkslateblue,R.color.royalblue,R.color.darkslategray,R.color.darkgreen,R.color.themeLight};
    public static final String[] TOOL_BAR_TITLE ={"","","","",""};
    public enum REMINDER_LEVEL{HIGH,NORMAL,LOW}
    public enum DEFAULT_ICON{LEARNING}
    public  static final Reminder.ALARM_TIME[] ALARM_TIME_ARRAY = {Reminder.ALARM_TIME.NOT_ALARM,Reminder.ALARM_TIME.INTIME,Reminder.ALARM_TIME.BEFORE5,Reminder.ALARM_TIME.BEFORE10,Reminder.ALARM_TIME.BEFORE30,Reminder.ALARM_TIME.BEFORE45,Reminder.ALARM_TIME.BEFORE60};
    public static final String[] MONTH_SHORT_EN = {"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
    public static final Integer[] LIST_COLOR_ICON = {R.drawable.ic_color_alarm,R.drawable.ic_color_audio_book,R.drawable.ic_color_baby,R.drawable.ic_color_badminton,R.drawable.ic_color_book,
            R.drawable.ic_color_bowling,R.drawable.ic_color_cafe,R.drawable.ic_color_car1,R.drawable.ic_color_cat,R.drawable.ic_color_check_book,
            R.drawable.ic_color_contruct,R.drawable.ic_color_cook1,R.drawable.ic_color_cook2,R.drawable.ic_color_cup,R.drawable.ic_color_cycling,
            R.drawable.ic_color_dog,R.drawable.ic_color_flower,R.drawable.ic_color_game1,R.drawable.ic_color_game2,R.drawable.ic_color_gift,
            R.drawable.ic_color_house_keeper1,R.drawable.ic_color_internet,R.drawable.ic_color_mail,R.drawable.ic_color_meal,R.drawable.ic_color_music,
            R.drawable.ic_color_music1,R.drawable.ic_color_paint,R.drawable.ic_color_party,R.drawable.ic_color_phone,R.drawable.ic_color_play,
            R.drawable.ic_color_radio,R.drawable.ic_color_relax,R.drawable.ic_color_report,R.drawable.ic_color_retaurant,R.drawable.ic_color_reward,
            R.drawable.ic_color_school,R.drawable.ic_color_shopping,R.drawable.ic_color_sleep,R.drawable.ic_color_sleep1,R.drawable.ic_color_soccer,
            R.drawable.ic_color_tooth,R.drawable.ic_color_traning,R.drawable.ic_color_trip,R.drawable.ic_color_tv,R.drawable.ic_color_tv2,
            R.drawable.ic_color_work,R.drawable.ic_color_yoga,R.drawable.ic_bell,R.drawable.ic_graduate,R.drawable.ic_reward};
}
