package pmf.android.sportscoresbysbm2.data.model;

import com.google.gson.annotations.SerializedName;


public class Season {

    @SerializedName("year")
    private int year;
    @SerializedName("start_date")
    private String startDate;
    @SerializedName("end_date")
    private String endDate;
    @SerializedName("current")
    private boolean current;
    @SerializedName("coverage")
    private Coverage coverage;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public Coverage getCoverage() {
       return coverage;
   }

   public void setCoverage(Coverage coverage) {
      this.coverage = coverage;
    }
}

