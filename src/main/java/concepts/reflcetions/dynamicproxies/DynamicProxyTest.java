package concepts.reflcetions.dynamicproxies;

import org.testng.annotations.Test;

import java.lang.reflect.*;

public class DynamicProxyTest {

    @Test
    private void testDynamicProxy() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        /** Create a Dynamic Proxy Class **/
        Class<?> proxyClass = Proxy.getProxyClass(ClassLoader.getSystemClassLoader(), AboutMe.class);


        /**  Create Dynamic Proxy class constructor  **/
        Constructor<?> proxyConstructor = proxyClass.getConstructor(InvocationHandler.class);

        /** Create a Invocation Handler implementation **/
        InvocationHandler handler = new InvocationHandler(){

            /** This invoke method will be called for every proxy method call.
             * You can change the way what it returns or does.**/
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();

                if(methodName.equals("getName")){
                    System.out.println("Overriding the getName() method and returning custom name");
                    return "Rentala";
                }
                if(methodName.equals("getAge")){
                    System.out.println("Overriding the getAge() method and returning custom age");
                    return 27;
                }
                /* Use this to see which all methids are invoked */
                //System.out.println("Unkown method called!");
                return null;
            }
        };

        /** Create dynamic proxy instance **/
        AboutMe aboutMe = (AboutMe) proxyConstructor.newInstance(handler);
        AboutMe aboutMe1 = new AboutMeImplementation();

        /** Invoke methods **/
        aboutMe.getName();
        aboutMe1.getAge();
    }
}
