public class CinemaSeat extends LongDistanceBusSeat {

    private int numberOfSeat;
    private boolean seatTaken;

    public int calculateNumberOfSeat() {
        int from = 0;
        int before = 120;
        return from + (int) (Math.random() * before);
    }

    public int getNumberOfSeat() {
        return numberOfSeat;
    }

    public void setNumberOfSeat(int numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
    }

    public boolean isSeatTaken() {
        return seatTaken;
    }

    public void setSeatTaken(boolean seatTaken) {
        this.seatTaken = seatTaken;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        CinemaSeat other = (CinemaSeat) obj;
        return numberOfSeat == other.numberOfSeat && isSeatTaken() == other.isSeatTaken();
    }

    @Override
    public String toString() {
        return "Это место номер" + numberOfSeat;

    }
}
