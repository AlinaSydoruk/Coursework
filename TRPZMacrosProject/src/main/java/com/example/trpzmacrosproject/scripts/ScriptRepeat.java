package com.example.trpzmacrosproject.scripts;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.scheduling.support.CronTrigger;

//sek, min, hours, days, month, dayOfWeek
@NoArgsConstructor
@AllArgsConstructor
public class    ScriptRepeat {
    /**Конкретні дати, при невизначенні будуть замінені на "*"   */
    Integer exactSecond, exactMinute, exactHour, exactDayOfMonth, exactMonth, exactDayOfWeek;



    public CronTrigger getCronTrigger(){
        StringBuilder sb = new StringBuilder();
        sb.append(exactSecond == null? "*" : exactSecond).append(" ");
        sb.append(exactMinute == null? "*" : exactMinute).append(" ");
        sb.append(exactHour == null? "*" : exactHour).append(" ");
        sb.append(exactDayOfMonth == null? "*" : exactDayOfMonth).append(" ");
        sb.append(exactMonth == null? "*" : exactMonth).append(" ");
        sb.append(exactDayOfWeek == null? "*" : convertDayOfWeek(exactDayOfWeek));
        return new CronTrigger(sb.toString());
    }

    private String convertDayOfWeek(Integer day){
        if (day == null){
            return "*";
        }
        return switch (day) {
            case 1 -> "MON";
            case 2 -> "TUE";
            case 3 -> "WED";
            case 4 -> "THU";
            case 5 -> "FRI";
            case 6 -> "SAT";
            case 7 -> "SUN";
            default -> "*";
        };
    }
    public void setExactSecond(int exactSecond) {
        if (exactSecond < 0 || exactSecond > 59){
            throw new IllegalArgumentException("Invalid second: "+ exactSecond);
        }
        this.exactSecond = exactSecond;
    }

    public void setExactMinute(int exactMinute) {
        if (exactMinute < 0 || exactMinute > 59){
            throw new IllegalArgumentException("Invalid minutes: "+ exactMinute);
        }
        this.exactMinute = exactMinute;
    }

    public void setExactHour(int exactHour) {
        if (exactHour < 0 || exactHour > 23){
            throw new IllegalArgumentException("Invalid hour: "+ exactHour);
        }
        this.exactHour = exactHour;
    }

    /**
     * Виставлення конкретного дня місяця. Наприклад: 15 -> кожен місяць 15-ого числа
     * Примітка: Якщо виставити невалідний день (наприклад, 30-те число лютого), то код
     * не буде виконуватись (бо 30-те число не наступить).
     * @param exactDayOfMonth день місяця
     */
    public void setExactDayOfMonth(int exactDayOfMonth) {
        if (exactDayOfMonth < 0 || exactDayOfMonth > 31){
            throw new IllegalArgumentException("Invalid day of month: "+ exactDayOfMonth);
        }
        this.exactDayOfMonth = exactDayOfMonth;
    }

    public void setExactMonth(int exactMonth) {
        if (exactMonth < 1 || exactMonth > 12){
            throw new IllegalArgumentException("Invalid month: "+ exactMonth);
        }
        this.exactMonth = exactMonth;
    }

    public void setExactDayOfWeek(int exactDayOfWeek) {
        if (exactDayOfWeek < 0 || exactDayOfWeek > 7){
            throw new IllegalArgumentException("Invalid dayOfWeek: "+ exactDayOfWeek);
        }
        this.exactDayOfWeek = exactDayOfWeek;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("ScriptRepeat{");
        boolean isAnyFieldPresent = false;
        if (exactSecond != null) {
            builder.append("exactSecond=").append(exactSecond);
            isAnyFieldPresent = true;
        }
        if (exactMinute != null) {
            if (isAnyFieldPresent) builder.append(", ");
            builder.append("exactMinute=").append(exactMinute);
            isAnyFieldPresent = true;
        }
        if (exactHour != null) {
            if (isAnyFieldPresent) builder.append(", ");
            builder.append("exactHour=").append(exactHour);
            isAnyFieldPresent = true;
        }
        if (exactDayOfMonth != null) {
            if (isAnyFieldPresent) builder.append(", ");
            builder.append("exactDayOfMonth=").append(exactDayOfMonth);
            isAnyFieldPresent = true;
        }
        if (exactMonth != null) {
            if (isAnyFieldPresent) builder.append(", ");
            builder.append("exactMonth=").append(exactMonth);
            isAnyFieldPresent = true;
        }
        if (exactDayOfWeek != null) {
            if (isAnyFieldPresent) builder.append(", ");
            builder.append("exactDayOfWeek=").append(exactDayOfWeek);
            isAnyFieldPresent = true;
        }
        if (!isAnyFieldPresent) {
            builder.append("EverySecond");
        }
        builder.append('}');
        return builder.toString();
    }
}
