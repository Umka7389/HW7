import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class TestClass {

    public static void start(Class inputClass) throws Exception {
        int countBefore = 0;
        int countAfter = 0;

        Object objOfInputClass = inputClass.newInstance();
        ArrayList<Method> testMethods = new ArrayList();

        for (Method m : inputClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                countBefore++;
            } else if (m.isAnnotationPresent(AfterSuite.class)) {
                countAfter++;
            }
        }
        if (countBefore != 1 || countAfter != 1) {
            throw new RuntimeException("Аннотации before/after не в единственном экземпляре");
        }
        for (Method m : inputClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                m.invoke(objOfInputClass);
            }
        }

        for (Method m : inputClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                if(m.getAnnotation(Test.class).priority() < 1 || m.getAnnotation(Test.class).priority() > 10){
                    throw new RuntimeException("Приоритет за пределами 1-10");
                }
                testMethods.add(m);
            }
        }
        testMethods.sort(new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                return o1.getAnnotation(Test.class).priority()-o2.getAnnotation(Test.class).priority();
            }
        });
        for (Method m:testMethods) {
            m.invoke(objOfInputClass);
        }


        for (Method m : inputClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(AfterSuite.class)) {
                m.invoke(objOfInputClass);
            }
        }

    }
}
