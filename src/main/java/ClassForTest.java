public class ClassForTest {

    @BeforeSuite
    public void beforeSuiteTest(){
        System.out.println("This run before all tests");
    }

    @Test(priority = 1)
    public void test_1(){
        System.out.println("This run first test");
    }

    @Test(priority = 2)
    public void test_2(){
        System.out.println("This run second_random test");
    }

    @Test(priority = 3)
    public void test_3(){
        System.out.println("This run third test");
    }

    @Test(priority = 2)
    public void test_2_1(){
        System.out.println("This run second_random test");
    }

    @AfterSuite
    public void afterSuiteTest(){
        System.out.println("This run after all tests");
    }


}
