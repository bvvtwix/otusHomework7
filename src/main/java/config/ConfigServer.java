package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:config.properties")
public interface ConfigServer extends  Config{
    @Key("urlOtus")
    String urlOtus();
    @Key("email")
    String email();
    @Key("pass")
    String pass();
}
