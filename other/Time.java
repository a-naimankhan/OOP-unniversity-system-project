package other;

import java.util.Objects;

public class Time{
	int hour;
	int minute;
	int second;
	
	public Time(int hour, int minute, int second){
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	public void setHour(int hour) {
		this.hour = hour;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	public void setTime(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	public int getHour() {
		return this.hour;
	}
	public int getMinute() {
		return this.minute;
	}
	public int getSecond() {
		return this.second;
	}
	
	public boolean isOk(int hour, int minute, int second) {
		if (this.hour >= 0 && this.hour < 24 && this.minute >= 0 && this.minute < 60 && this.second >= 0 && this.second < 60) return true;
		return false;
	}
	
	public String  toString(int value) {
		if (value < 10) {
			return "0"+Integer.toString(value);
		}
		return Integer.toString(value);
	}
	
	public String toUniversal() {
		return toString(hour) + ":" + toString(minute) + ":" + toString(second);
	}
	
	public String toStandard() {
		String standardTime = toString(hour % 12) + ":" + toString(minute) + ":" + toString(second);
		if (hour > 12 && hour <= 23) standardTime += " PM";
		else standardTime += " AM";
		
		return standardTime;
	}
	
	public void timeSetting() {
		    this.minute += this.second / 60;
		    this.second %= 60;
		    this.hour += this.minute / 60;
		    this.minute %= 60;
	}
	public static Time addTimes(Time time1, Time time2) {
		Time resultTime = new Time(time1.hour + time2.hour, time1.minute + time2.minute, time1.second + time2.second);
		resultTime.timeSetting();
		return resultTime;
	}
	
	public Time addTimes(Time time1) {
	    return Time.addTimes(time1, this);
	}

	@Override
	public int hashCode() {
		return Objects.hash(hour, minute, second);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Time other = (Time) obj;
		return hour == other.hour && minute == other.minute && second == other.second;
	}  
}
