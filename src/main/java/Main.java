public class Main {
    public static void main(String[] args) throws Exception {
    TestClass test = new TestClass();
    ClassForTest classForTest = new ClassForTest();
    test.start(classForTest.getClass());
}

}
