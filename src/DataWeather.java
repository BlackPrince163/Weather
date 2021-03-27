import java.util.Date;

public class DataWeather {
    double temp;
    double humidity;
    double windSpeed;
    double directionWind;
    String date;

    public DataWeather(String date, double temp, double humidity, double windSpeed, double directionWind) {
        this.date = trimDateAndTime(date);
        this.temp = temp;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.directionWind = Math.round(directionWind);
    }


    public String trimDateAndTime(String date) {
        String newDate =date.substring(6, 8) + "-" +  date.substring(4, 6) + "-" + date.substring(0,4);
        String newTime = date.substring(9,11) + ":" + date.substring(11, 13);
        String newDateAndTime = newDate + " " +  newTime;
        return newDateAndTime;


        /*String str = "20211212";
        String year = str.substring(0, 4);
        System.out.println(year);*/
    }



    @Override
    public String toString() {
        return "DataWeather{" +
                "date='" + date + '\'' +
                ", temp=" + temp +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                ", directionWind=" + directionWind +
                '}';
    }
}
