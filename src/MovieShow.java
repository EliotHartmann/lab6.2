public class MovieShow {
    private boolean movieIsOn;

    public String startMovieShow(){
        this.movieIsOn = true;
        return "Фильм начался";
    }

    public String endMovieShow(){
        this.movieIsOn = false;
        return "фильм закончился";
    }

    public boolean isMovieIsOn() {
        return movieIsOn;
    }

    @Override
    public String toString(){
        return this.movieIsOn ? "Фильм идет" : "Фильм не идет";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass() )
            return false;
        MovieShow other = (MovieShow) obj;
        return movieIsOn == other.movieIsOn;
    }
}
