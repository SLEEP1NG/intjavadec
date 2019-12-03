package annotations;

import java.io.FileReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

public class Framework {
    public static void main(String[] args) throws Throwable {
//        System.setSecurityManager(new SecurityManager());

        Properties prop = new Properties();
        prop.load(new FileReader("totest.properties"));

        String className = prop.getProperty("test1");

//        System.setSecurityManager(new SecurityManager());

        Class<?> clazz = Class.forName(className);

        System.out.println("Loaded class called: " + clazz.getName());

        Constructor<?> zeroArgCons = clazz.getConstructor();
        Object obj = zeroArgCons.newInstance();
        System.out.println("obj is " + obj);

        Method toString = clazz.getDeclaredMethod("toString");
        Class<?> retType = toString.getReturnType();
        System.out.println("Return type of toString is " + retType.getName());
        String rv = (String)toString.invoke(obj);
        System.out.println("Invoking toString returned " + rv);

        Field nameField = clazz.getDeclaredField("name");
        SetMe setAnnot = nameField.getAnnotation(SetMe.class);
        if (setAnnot != null) {
            String key = setAnnot.value();
            int count = setAnnot.count();
            System.out.println("SetMe annotation found on name, value is "
                    + key + " count is " + count);
            nameField.setAccessible(true);
            nameField.set(obj, prop.getProperty(key));
            System.out.println("toString now returns: " + toString.invoke(obj));
        }

        Method [] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println("> " + m);
            RunMe annot = m.getAnnotation(RunMe.class);
            if (annot != null) {
                System.out.println("**** RunMe annotation found!!!");
                // disable access checks
                m.setAccessible(true);
                m.invoke(obj);
            }
        }
    }
}
