package ch12.mylog;

import java.lang.reflect.*;

public class MyAopProxy {
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(T target) {
        Class<?> cls = target.getClass();

        return (T) Proxy.newProxyInstance(
                cls.getClassLoader(),
                cls.getInterfaces(),  // 인터페이스 기반 프록시만 가능
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Method realMethod = cls.getMethod(method.getName(), method.getParameterTypes());

                        if (realMethod.isAnnotationPresent(MyLog.class)) {
                            System.out.println("📢 [LOG] " + method.getName() + "() 메서드 호출됨");
                        }

                        return method.invoke(target, args);
                    }
                }
        );
    }
}

