import java.util.Objects;

public class Route implements Comparable<Route> {
    private String id;
    private double distance;
    private int popularity;
    private boolean isFavorite;
    private MyList<String> locationPoints;

    public Route(String id, double distance, int popularity, boolean isFavorite, MyList<String> locationPoints) {
        this.id = id;
        this.distance = distance;
        this.popularity = popularity;
        this.isFavorite = isFavorite;
        this.locationPoints = locationPoints;
    }

    public Route() {

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Route route = (Route) obj;
        return Objects.equals(id, route.id);
    }
    public boolean pointsEqual(Route otherRoute) {
        return this.locationPoints.get(0).equals(otherRoute.locationPoints.get(0)) &&
                this.locationPoints.get(this.locationPoints.size() - 1).equals(otherRoute.locationPoints.get(otherRoute.locationPoints.size() - 1));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public MyList<String> getLocationPoints() {
        return locationPoints;
    }

    public void setLocationPoints(MyList<String> locationPoints) {
        this.locationPoints = locationPoints;
    }

    @Override
    public int compareTo(Route o) {
        int favoriteComparison = Boolean.compare(o.isFavorite(), this.isFavorite());
        if (favoriteComparison != 0) {
            return favoriteComparison;
        }
        if (this.getId() != null && o.getId() != null) {
            return this.getId().compareTo(o.getId());
        }
        return 0;
    }


    @Override
    public String toString() {
        return "Route{" +
                "id='" + id + '\'' +
                ", distance=" + distance +
                ", popularity=" + popularity +
                ", isFavorite=" + isFavorite +
                ", locationPoints=" + locationPoints +
                '}';
    }
}
