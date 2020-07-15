package com.demo.reflect;

public class ReflectTarget extends ReflectTargetOrigin{

    public String str;
    protected int index;
    char type;
    private String targetInfo;

    public ReflectTarget(String str){
        this.str = str;
        System.out.println("带参数构造函数String：" + str);
    }

    public ReflectTarget(char c){
        System.out.println("带参数构造函数char：" + c);
    }

    public ReflectTarget(){
        System.out.println("无参构造函数");
    }

    public ReflectTarget(String key, int value){
        System.out.println("多参数构造函数key：" + key + " , value：" + value);
    }

    protected ReflectTarget(boolean n){
        System.out.println("受保护的构造方法n：" + n);
    }

    private ReflectTarget(int index){
        System.out.println("私有的构造方法index：" + index);
    }

    void show1(){
        System.out.println("show1");
    }

    public void show2(){
        System.out.println("show2");
    }

    protected void show3(){
        System.out.println("show3");
    }

    private String show4(int index){
        System.out.println("index：" + index);
        return String.valueOf(index);
    }


    @Override
    public String toString() {
        return "ReflectTarget{" +
                "str='" + str + '\'' +
                ", index=" + index +
                ", type=" + type +
                ", targetInfo='" + targetInfo + '\'' +
                '}';
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //获取class对象
        ReflectTarget reflectTarget = new ReflectTarget();
        Class<? extends ReflectTarget> reflectTargetClass1 = reflectTarget.getClass();
        System.out.println(reflectTargetClass1.getName());
        //
        Class<ReflectTarget> reflectTargetClass2 = ReflectTarget.class;
        System.out.println(reflectTargetClass2.getName());
        //
        Class<?> reflectTargetClass3 = Class.forName("com.demo.reflect.ReflectTarget");
        System.out.println(reflectTargetClass3.getName());
    }
}
