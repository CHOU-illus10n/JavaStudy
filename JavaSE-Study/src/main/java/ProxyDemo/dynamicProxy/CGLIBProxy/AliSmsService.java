package ProxyDemo.dynamicProxy.CGLIBProxy;

public class AliSmsService {
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}