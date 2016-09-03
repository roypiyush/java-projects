package com.personal.di;

import org.glassfish.hk2.api.Descriptor;
import org.glassfish.hk2.api.DynamicConfiguration;
import org.glassfish.hk2.api.DynamicConfigurationService;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.api.ServiceLocatorFactory;
import org.glassfish.hk2.utilities.BuilderHelper;

public class MainRunnerDI {

	public static void main(String... args) {
		ServiceLocatorFactory factory = ServiceLocatorFactory.getInstance();
        
        ServiceLocator locator = factory.create("HelloWorld");
        
        DynamicConfigurationService dcs = locator.getService(DynamicConfigurationService.class);
        
        DynamicConfiguration config = dcs.createDynamicConfiguration();
        
        config.bind(createWidgetDescriptor());
        
        config.commit();
        
        
        Foo service = locator.getService(Foo.class);
        service.print("dfdfdfd");
        
	}
	
	static Descriptor createWidgetDescriptor() {
        return BuilderHelper.link("com.personal.di.FooImpl").
                         to("com.personal.di.Foo").
                         in("org.glassfish.api.PerLookup").
                         build();
    }
}
