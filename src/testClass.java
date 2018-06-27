public class testClass {

    private int primary_key;

    private boolean k;

    testClass(int a, boolean k){
        this.primary_key = a;
        this.k = k;
    }
    testClass(){}

    @toJson
    testClass2 testClass2Obj = new testClass2(1, 23.575, true);

    testClass2 TestClass2Obj2 = new testClass2(2, 46.467748, false);
//    testClass3 testClass3 = new testClass3(1, 234, 234);
}
