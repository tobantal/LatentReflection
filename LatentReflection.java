import java.lang.reflect.*;

interface Performs {
    void speak();
    void sit();
}

class Robot implements Performs {
    public void speak() { System.out.println("Click!"); }
    public void sit() { System.out.println("Clank!"); }
    public void oilChange() {}
}

class Mime {
    public void walkAgainstTheWind() {}
    public void sit() { System.out.println("Pretending to sit "); }
    public void pushInvisibleWalls() {}
    public String toString() {
        return "Mime";
    }
}

class SmartDog {
    public void speak() { System.out.println("Woof!"); }
    public void sit() { System.out.println("Sitting"); }
    public void reproduce() {}
}

class CommunicateReflectively {

    public static void perform(Object speaker) {
        Class<?> spkr = speaker.getClass();
        try {
            try {
                Method speak = spkr.getMethod("speak");
                speak.invoke(speaker);
            } catch (Exception e) {
                System.out.println(speaker + " cannot speak");
            }
            try {
                Method speak = spkr.getMethod("sit");
                speak.invoke(speaker);
            } catch (Exception e) {
                System.out.println(speaker + " cannot sit");
            }
        } catch (Exception e) {
            throw new RuntimeException(speaker.toString(), e);
        }
    }
}


public class LatentReflection {
    public static void main(String[] args) {
        CommunicateReflectively.perform(new SmartDog());
        CommunicateReflectively.perform(new Robot());
        CommunicateReflectively.perform(new Mime());
    }
}