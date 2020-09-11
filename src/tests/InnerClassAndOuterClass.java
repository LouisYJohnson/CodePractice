package tests;

//主类(有main方法的就是主类)
public class InnerClassAndOuterClass {
    public static void main(String[] args) {
        Father.testMethod();
        Son.testMethod();
        Test1.staticTest();
    }

    //内部类
    static class Father{
        public static void testMethod() {
            System.out.println("Father");
        }
    }

    static class Son extends Father{
        public static void testMethod() {
            System.out.println("Son");
        }
    }
}

//外部类
class Test1 {
    public static void staticTest() {
        System.out.println(111);
    }
}
