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

    public static void perform(Object obj) {
        try {
            tryMethod("speak", obj);
            tryMethod("sit", obj);
        } catch (Exception e) {
            throw new RuntimeException(obj.toString(), e);
        }
    }

    private static void tryMethod(String methodName, Object obj) {
        Class<?> aClass = obj.getClass();
        try {
            Method method = aClass.getMethod(methodName);
            method.invoke(obj);
        } catch (Exception e) {
            System.out.println(obj + " cannot " + methodName);
        }
    }
}


public class LatentReflection {
    public static void main(String[] args) {
        CommunicateReflectively.perform(new SmartDog());
        CommunicateReflectively.perform(new Robot());
        CommunicateReflectively.perform(new Mime());
        CommunicateReflectively.perform(Integer.valueOf(123));
        CommunicateReflectively.perform(new String("abc"));
    }
}