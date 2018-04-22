public class LongDistanceBusSeat {
    final boolean softSeats = true;
    final boolean foldingBackrests = true;
    private boolean isBackrestFolded;

    public String toFoldBackrest(){
        this.isBackrestFolded = true;
        return "Спинка откинута";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass() )
            return false;
        LongDistanceBusSeat other = (LongDistanceBusSeat) obj;
        return isBackrestFolded == other.isBackrestFolded;
    }
    @Override
    public String toString(){
        if (isBackrestFolded)
            return "Кресло мягкое, с откидывающейся спинкой, спинка откинута";
        else
            return "Кресло мягкое, с откидывающейся спинкой, спинка поднята";
    }
}
