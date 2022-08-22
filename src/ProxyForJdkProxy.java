import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Person {
    void tellName();
}

public class ProxyForJdkProxy {
    public static void main(String[] args) {
        PersonImpl person = new PersonImpl();
        PersonInvocationHandler handler = new PersonInvocationHandler(person);
        Person proxyPerson = (Person) Proxy.newProxyInstance(PersonImpl.class.getClassLoader(), PersonImpl.class.getInterfaces(), handler);
        proxyPerson.tellName();
    }
}

class PersonImpl implements Person {
    @Override
    public void tellName() {
        System.out.println("Hello ，my name is Java");
    }
}

class PersonInvocationHandler implements InvocationHandler {
    private final Object target;

    public PersonInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Person says his name is Java");
        return method.invoke(target, args);
    }
}
