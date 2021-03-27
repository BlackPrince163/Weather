import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AnalysisFile {


    public static void main(String[] args) {
        String path = "C:\\Users\\niazh\\IdeaProjects\\Weather\\src\\data.csv";
        String line = "";
        double averageTemp = 0; // средняя температура
        double averageHumidity = 0; // средняя влажность
        double averageWindSpeed = 0; // ср скорость ветра
        int count = 0; // счетчик строк
        double maxTemp = 0;
        String maxTempDate = "";

        double lowHumidity = 9999999;
        String lowHumidityDate = "";
        double strongWind = 0;
        String strongWindDate = "";

        String sideOfWorld = "";


        int south = 0;
        int north = 0;
        int west = 0;
        int east = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            for (int i = 0; i < 10; i++) {
                br.readLine();
            }
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                DataWeather dataWeather = new DataWeather(values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2]), Double.parseDouble(values[3]), Double.parseDouble(values[4]));
                /*System.out.println(dataWeather.directionWind);*/
                count++;

                averageTemp += dataWeather.temp;
                averageHumidity += dataWeather.humidity;
                averageWindSpeed += dataWeather.windSpeed;

                if (dataWeather.temp > maxTemp) {
                    maxTemp = dataWeather.temp;
                    maxTempDate = dataWeather.date;
                }

                if (dataWeather.humidity < lowHumidity) {
                    lowHumidity = dataWeather.humidity;
                    lowHumidityDate = dataWeather.date;
                }

                if (dataWeather.windSpeed > strongWind) {
                    strongWind = dataWeather.windSpeed;
                    strongWindDate = dataWeather.date;
                }

                if ((dataWeather.directionWind > 315) && dataWeather.directionWind < 45)
                    north++;

                if ((dataWeather.directionWind >= 45) && dataWeather.directionWind < 135)
                    east++;

                if ((dataWeather.directionWind >= 135) && dataWeather.directionWind < 225)
                    south++;

                if ((dataWeather.directionWind >= 225) && dataWeather.directionWind < 315)
                    west++;




            }
            if ((north > east) && (north > south) && (north > west))
                sideOfWorld = "north";

            if ((east > north) && (east > south) && (east > west))
                sideOfWorld = "east";

            if ((south > east) && (south > north) && (south > west))
                sideOfWorld = "south";

            if ((west > east) && (west > south) && (west > north))
                sideOfWorld = "west";





            averageTemp = averageTemp/count;
            averageHumidity = averageHumidity/count;
            averageWindSpeed = averageWindSpeed/count;
           /* System.out.println(averageTemp);
            System.out.println(averageHumidity);
            System.out.println(averageWindSpeed);*/
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            File file = new File("newFile.txt");
            if (!file.exists())
                file.createNewFile();
            PrintWriter pw = new PrintWriter(file);
            pw.println("Средняя температура: " + averageTemp);
            pw.println("Средняя влажность: " + averageHumidity);
            pw.println("Средняя скорость ветра: " + averageWindSpeed);
            pw.println("Самая высокая температура была в " + maxTempDate);
            pw.println("Самая низкая влажность была в " + lowHumidityDate);
            pw.println("Самый сильный ветер был " + strongWindDate);
            pw.println("Самое частое направление ветра: " + sideOfWorld);
            pw.close();
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
