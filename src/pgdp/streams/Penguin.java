package pgdp.streams;

import java.util.List;

public class Penguin {
  private List<Geo> locations;
  private String trackID;

  public Penguin(List<Geo> locations, String trackID) {
    this.locations = locations;
    this.trackID = trackID;
  }

  @Override
  public String toString() {
    return "Penguin{" +
        "locations=" + locations +
        ", trackID='" + trackID + '\'' +
        '}';
  }

  public List<Geo> getLocations() {
    return locations;
  }

  public String getTrackID() {
    return trackID;
  }

  public String toStringUsingStreams() {
    StringBuilder sb = new StringBuilder();
    sb.append("Penguin{locations=[");
    locations.stream().sorted((l1, l2) -> {
      if (l1.getLatitude() > l2.getLatitude()) return -1;
      if (l1.getLatitude() < l2.getLatitude()) return 1;
      return Double.compare(l2.getLongitude(), l1.getLongitude());
    }).forEach(p -> sb.append(p).append(", "));
    sb.append("], trackID='").append(trackID).append("'}");
    return sb.toString();
  }
}
