public class CinemaRoom {
    public String startMovie() {
        Light light = new Light();
        MovieShow movieShow = new MovieShow();
        return light.lightTurnOff() + movieShow.startMovieShow();

    }
    public String becomeQuiter(){
        Noize noize = new Noize();
        int volumeBefore = noize.getNoizeVolume();
        int volumeDifference;
        noize.setNoizeVolume(noize.getMIN_VOLUME() + (int)(Math.random()*volumeBefore));
        volumeDifference = volumeBefore - noize.getNoizeVolume();
        return "Уровень шума в зале уменьшился на " + volumeDifference + "%";
    }

    public String NoizeOff(){
        Noize noize = new Noize();
        noize.setNoizeVolume(noize.getMIN_VOLUME());
        return "Шум утих";
    }
    public String endMovie() {
        Light light = new Light();
        MovieShow movieShow = new MovieShow();
        return light.lightTurnOn() + movieShow.endMovieShow();

    }
}
