package hr.algebra.java2orlog.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class JndiHelper {
    private static final String PROVIDER_URL = "file:C:\\Users\\dbasi\\Desktop\\JAVA2projekt";
    private static final String CONFIGURATION_FILE_NAME = "conf.properties";

    private static InitialContext context;

    private static InitialContext getInitialContext() throws NamingException {
        if (context == null) {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
            props.setProperty(Context.PROVIDER_URL, PROVIDER_URL);
            context = new InitialContext(props);
        }

        return context;
    }

    public static String getConfigurationParameter(JndiKeyEnum param) throws NamingException, IOException {
        Object configurationFileName = getInitialContext().lookup(CONFIGURATION_FILE_NAME);
        Properties configurationProperties = new Properties();
        configurationProperties.load(new FileReader(configurationFileName.toString()));
        return configurationProperties.getProperty(param.getKey());
    }

}
