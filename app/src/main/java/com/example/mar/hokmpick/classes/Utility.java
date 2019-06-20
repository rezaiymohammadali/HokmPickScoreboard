package com.example.mar.hokmpick.classes;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by JFP on 11/22/2017.
 */

public class Utility {

    public String strWeekDay = "";
    public String strMonth = "";

    public String nowDate() {

        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
                + calendar.get(Calendar.DAY_OF_MONTH) + " "
                + calendar.getTime().getHours() + ":" + calendar.getTime().getMinutes() + ":"
                + calendar.getTime().getSeconds();
    }

    public String nowdate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH);

    }


    private class SolarCalendar {


        int date;
        int month;
        int year;

        public SolarCalendar() {
            Date MiladiDate = new Date();
            calcSolarCalendar(MiladiDate);
        }

        public SolarCalendar(Date MiladiDate) {
            calcSolarCalendar(MiladiDate);
        }

        private void calcSolarCalendar(Date MiladiDate) {

            int ld;

            int miladiYear  = MiladiDate.getYear() + 1900 ;
            int miladiMonth = MiladiDate.getMonth() + 1   ;
            int miladiDate  = MiladiDate.getDate()        ;
            int WeekDay     = MiladiDate.getDay()         ;

            int[] buf1 = new int[12];
            int[] buf2 = new int[12];

            buf1[0]  = 0  ;
            buf1[1]  = 31 ;
            buf1[2]  = 59 ;
            buf1[3]  = 90 ;
            buf1[4]  = 120;
            buf1[5]  = 151;
            buf1[6]  = 181;
            buf1[7]  = 212;
            buf1[8]  = 243;
            buf1[9]  = 273;
            buf1[10] = 304;
            buf1[11] = 334;

            buf2[0]  = 0  ;
            buf2[1]  = 31 ;
            buf2[2]  = 60 ;
            buf2[3]  = 91 ;
            buf2[4]  = 121;
            buf2[5]  = 152;
            buf2[6]  = 182;
            buf2[7]  = 213;
            buf2[8]  = 244;
            buf2[9]  = 274;
            buf2[10] = 305;
            buf2[11] = 335;

            if ((miladiYear % 4) != 0) {
                date = buf1[miladiMonth - 1] + miladiDate;
                if (date > 79) {
                    date = date - 79;
                    if (date <= 186) {
                        switch (date % 31) {
                            case 0:
                                month = date / 31;
                                date = 31;
                                break;
                            default:
                                month = (date / 31) + 1;
                                date = (date % 31);
                                break;
                        }
                        year = miladiYear - 621;
                    } else {
                        date = date - 186;

                        switch (date % 30) {
                            case 0:
                                month = (date / 30) + 6;
                                date = 30;
                                break;
                            default:
                                month = (date / 30) + 7;
                                date = (date % 30);
                                break;
                        }
                        year = miladiYear - 621;
                    }
                } else {
                    if ((miladiYear > 1996) && (miladiYear % 4) == 1) {
                        ld = 11;
                    } else {
                        ld = 10;
                    }
                    date = date + ld;
                    switch (date % 30) {
                        case 0:
                            month = (date / 30) + 9;
                            date = 30;
                            break;
                        default:
                            month = (date / 30) + 10;
                            date = (date % 30);
                            break;
                    }
                    year = miladiYear - 622;
                }
            } else {
                date = buf2[miladiMonth - 1] + miladiDate;
                if (miladiYear >= 1996) {
                    ld = 79;
                } else {
                    ld = 80;
                }
                if (date > ld) {
                    date = date - ld;
                    if (date <= 186) {
                        switch (date % 31) {
                            case 0:
                                month = (date / 31);
                                date = 31;
                                break;
                            default:
                                month = (date / 31) + 1;
                                date = (date % 31);
                                break;
                        }
                        year = miladiYear - 621;
                    } else {
                        date = date - 186;
                        switch (date % 30) {
                            case 0:
                                month = (date / 30) + 6;
                                date = 30;
                                break;
                            default:
                                month = (date / 30) + 7;
                                date = (date % 30);
                                break;
                        }
                        year = miladiYear - 621;
                    }
                } else {
                    date = date + 10;
                    switch (date % 30) {
                        case 0:
                            month = (date / 30) + 9;
                            date = 30;
                            break;
                        default:
                            month = (date / 30) + 10;
                            date = (date % 30);
                            break;
                    }
                    year = miladiYear - 622;
                }
            }

            switch (month) {
                case 1:
                    strMonth = "فروردين";
                    break;
                case 2:
                    strMonth = "ارديبهشت";
                    break;
                case 3:
                    strMonth = "خرداد";
                    break;
                case 4:
                    strMonth = "تير";
                    break;
                case 5:
                    strMonth = "مرداد";
                    break;
                case 6:
                    strMonth = "شهريور";
                    break;
                case 7:
                    strMonth = "مهر";
                    break;
                case 8:
                    strMonth = "آبان";
                    break;
                case 9:
                    strMonth = "آذر";
                    break;
                case 10:
                    strMonth = "دي";
                    break;
                case 11:
                    strMonth = "بهمن";
                    break;
                case 12:
                    strMonth = "اسفند";
                    break;
            }

            switch (WeekDay) {
                case 0:
                    strWeekDay = "يکشنبه";
                    break;
                case 1:
                    strWeekDay = "دوشنبه";
                    break;
                case 2:
                    strWeekDay = "سه شنبه";
                    break;
                case 3:
                    strWeekDay = "چهارشنبه";
                    break;
                case 4:
                    strWeekDay = "پنج شنبه";
                    break;
                case 5:
                    strWeekDay = "جمعه";
                    break;
                case 6:
                    strWeekDay = "شنبه";
                    break;
            }
        }
    }

    public static String getCurrentShamsidate() {
        Locale loc = new Locale("en_US");
        Utility util = new Utility();
        SolarCalendar sc = util.new SolarCalendar();
        String day = util.day_of_week();

        return String.valueOf(sc.year) + "/" + String.format(loc, "%02d",
                sc.month) + "/" + String.format(loc, "%02d", sc.date);
    }

    public String day_of_week() {
        Date MiladiDate = new Date();
        MiladiDate.setDate(6);
        MiladiDate.setMonth(12);
        MiladiDate.setYear(1396);
        SolarCalendar solarCalendar = new SolarCalendar();

        solarCalendar.calcSolarCalendar(MiladiDate);
        return strWeekDay;
    }

    public static String getCurrentTime() {

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat oSimpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String MyString= oSimpleDateFormat.format(cal.getTime());

        return MyString;
    }

    public String day_of_week(int year, int month, int day) {
        Calendar now = Calendar.getInstance();
        now.set(year, month, day);

        String[] strDays = new String[]{"یکشنبه", "دوشنبه", "سشنبه", "چهارشنبه", "پنجشنبه",
                "جمعه", "شنبه"};

        return strDays[now.get(Calendar.DAY_OF_WEEK)];
    }

    public static String numToPersion(String a){
        String[] pNum =new String[]{"۰","۱","۲","۳","۴","۵","۶","۷","۸","۹" };
        a=a.replace("0",pNum[0]);
        a=a.replace("1",pNum[1]);
        a=a.replace("2",pNum[2]);
        a=a.replace("3",pNum[3]);
        a=a.replace("4",pNum[4]);
        a=a.replace("5",pNum[5]);
        a=a.replace("6",pNum[6]);
        a=a.replace("7",pNum[7]);
        a=a.replace("8",pNum[8]);
        a=a.replace("9",pNum[9]);
        return a;
    }

    public static String numToEnglish(String a){
        String[] pNum =new String[]{"0","1","2","3","4","5","6","7","8","9" };
        a=a.replace("۰",pNum[0]);
        a=a.replace("۱",pNum[1]);
        a=a.replace("۲",pNum[2]);
        a=a.replace("۳",pNum[3]);
        a=a.replace("۴",pNum[4]);
        a=a.replace("۵",pNum[5]);
        a=a.replace("۶",pNum[6]);
        a=a.replace("۷",pNum[7]);
        a=a.replace("۸",pNum[8]);
        a=a.replace("۹",pNum[9]);
        return a;
    }

    public static void edtNumE2P(final EditText edt) {
        edt.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int pstart, int pbefore, int pcount) {
//        for (String chr : new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}) {
                for (char chr : "0123456789".toCharArray()) {
                    if (s.toString().contains("" + chr)) {
                        edt.setText(Utility.numToPersion(edt.getText().toString()));
                        edt.setSelection(edt.getText().length());
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private static Typeface typeFace;
    public static final int FONT_TYPE_REGULAR =1 ;
    public static final int FONT_TYPE_BOLD = 2 ;

    public static Typeface getTypeFace(Context context, int type){
        if (typeFace == null && type == FONT_TYPE_REGULAR)
        {
            typeFace = Typeface.createFromAsset(context.getAssets(), "IRANSansMobile.ttf");
        }else if(typeFace == null && type == FONT_TYPE_BOLD){
            typeFace = Typeface.createFromAsset(context.getAssets(), "IRANSansMobile_Bold.ttf");
        }
        return typeFace;
    }

}
