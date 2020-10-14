package common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date {

    //カレンダーを生成
    static Calendar cal = Calendar.getInstance();

    /**
     * YYYYMMDD型の日付を取得
     * @return
     */
    public static String getDateOfyyyyMMdd() {
        //フォーマットを設定して出力
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(cal.getTime());
    }

    /**
     * yyyy-MM-dd-HH:mm:ss型の時刻を取得
     * @return
     */
    public static String getTimeOfyyyyMMddHHmmss() {
        //フォーマットを設定して出力
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        return sdf.format(cal.getTime());
    }
}
