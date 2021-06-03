public class SecondsConverter {
    public static String convert(long seconds){
        if(seconds<0){
            return "Invalid seconds value";
        }
        long minutes = 0, hours = 0, days = 0;
        if(seconds%60!=0){
            minutes = seconds/60;
            seconds %= 60;
        }
        if(minutes%60!=0){
            hours = minutes/60;
            minutes %= 60;
        }
        if(hours%60!=0){
            days = hours/24;
            hours %= 24;
        }
        return "Days: "+days+", Hours: "+hours+", Minutes: "+minutes+", Seconds: "+seconds;
    }
}
