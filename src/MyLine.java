public class MyLine {
    private final String body;
    private final int year;
    private final int day;
    private final double rad_au;
    private final double se_lat;
    private final double se_lon;

    public MyLine(String body, String year, String day, String rad_au, String se_lat, String se_lon) {
        this.body = body;
        this.year = Integer.parseInt(year);
        this.day = Integer.parseInt(day);
        this.rad_au = Double.parseDouble(rad_au);
        this.se_lat = Double.parseDouble(se_lat);
        this.se_lon = Double.parseDouble(se_lon);

        if(!(this.year >= 0 && this.day >= 0))
            throw new NumberFormatException("day or year needs to be greater or equals 0");
    }

    public String getBody() {
        return body;
    }

    public int getDay() {
        return day;
    }

    public double getRadAu() {
        return rad_au;
    }

    public double getSeLat() {
        return se_lat;
    }

    public double getSeLon() {
        return se_lon;
    }
}
