package jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@PropertySource("classpath:application.properties")
public class DatabaseLookup {

    @Autowired
    private Environment env;

    public Map<String, String> get(String type) {
        return new HashMap<String, String>() {{
            put("driver", env.getProperty(type + ".driver"));
            put("url", env.getProperty(type + ".url"));
        }};
    }
}
