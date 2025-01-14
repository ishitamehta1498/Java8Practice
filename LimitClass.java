public final class LimitClass {
    private static LimitClass limitClass;
    private static int objCount=0;

    private LimitClass() {
        objCount++;
    }
    public static synchronized LimitClass getLimInstance(){
        if(objCount<3){
            limitClass=new LimitClass();
        }
        return limitClass;
    }
}
