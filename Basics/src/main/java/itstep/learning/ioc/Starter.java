package itstep.learning.ioc;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class Starter {
    @Inject private Service1 service1;
    @Inject private Service1 service1v2;
    @Inject private Service2 service2;
    @Inject private Service2 service2v2;
    @Inject private IService3 service3;
    @Inject @Named("v1") private IService4 service4v1;
    @Inject @Named("v2") private IService4 service4v2;

    @Inject @Named("MySqlConnectionString")
    private String myCs;
    @Inject @Named("OracleConnectionString")
    private String oraCs;

    @Inject CtrDemo ctrDemo;

    @Inject private IConfig config;

    public void run() {
        System.out.println("Di demo");
        service1.show();
        service1v2.show();
        service2.show();
        service2v2.show();
        service3.show();
        service4v1.show();
        service4v2.show();
        System.out.println(myCs);
        System.out.println(oraCs);
        ctrDemo.show();

        String value = config.getParameter("Key1");
        String value2 = config.getParameter("Key2");

        System.out.println("--------------------------------------------------------------");
        System.out.println(value);
        System.out.println(value2);
    }

}
