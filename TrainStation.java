
public class TrainStation {
	public String stationName;
	public String time;
	
	public TrainStation(String stationName,String time){
		this.stationName = stationName;
		this.time = time;
	}
	
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
