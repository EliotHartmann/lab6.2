import java.io.Serializable;
import java.awt.Rectangle;
public class Policeman implements Serializable{
    private int healthPoint;
    private String name;
    private int age;
    private String describition = "Вооруженные до зубов";
    private Transport transport;
    private int ID;
    int x1;
    int y1;
    int x2;
    int y2;
    int x3;
    int y3;
    int x4;
    int y4;

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public int getX3() {
        return x3;
    }

    public int getY3() {
        return y3;
    }

    public int getX4() {
        return x4;
    }

    public int getY4() {
        return y4;
    }

    private String colour;
    Policeman(String name, int age, String colour){
        this.name = name;
        this.age = age;
        this.colour = colour;
        this.x1 = -300 + (int)(Math.random()*600);
        this.y1 = -300 + (int)(Math.random()*600);
        this.x2 = x1;
        this.y2 = y1 + -200 + (int)(Math.random()*400);
        this.x3 = x2 + -200 + (int)(Math.random()*400);
        this.y3 = y2;
        this.x4 = x3;
        this.y4 = y1;
        }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String persueCriminal(){
        return "Полицейский " +  this.name + " гонится за преступником";
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }



    public String useVehicle(){
        this.transport = Transport.values()[(int)(Math.random()*Transport.values().length)];
        return this.name + " едет на " + this.transport.getName();
    }

    public String toFall(){
        this.healthPoint = healthPoint - 20;
        if (healthPoint <= 0)
            return "Полицейский" + this.name + " упал и умер";
        return this.name + " упал";
    }

    public String fallThrought(){
        this.healthPoint = healthPoint - 35;
        if (healthPoint <= 0)
            return "Полицейский " + this.name + " провалился и умер";
        return "Полицейский " + this.name + " провалился";
    }

    public String toSink(){
        this.healthPoint = 0;
        return this.name + " утонул";
    }

    public String sinkCriminal(Criminal criminal){
        criminal.setHealthPoint(0);
        return "Полицейский утопил преступника, по имени " + criminal.getName();
    }


    public String getHurt(){
        this.healthPoint = healthPoint - 30 + (int)(Math.random()*30);
        if (healthPoint <= 0)
            return "Полицейский " + name + " умирает";
        return "Полицейский " + name + " ранен";
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public String getDescribition() {
        return describition;
    }

    public void setDescribition(String describition) {
        this.describition = describition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "Это полицесйкий " + this.name + " и у него осталось " + this.healthPoint + " % здоровья";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass() )
            return false;
        Policeman other = (Policeman) obj;
        return name.equals(other.name) &&  healthPoint == other.healthPoint;
    }
}
