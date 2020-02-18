package jdbc;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Connection {
    private String username;
    private String password;
    private String host;
    private String port;
    private String database;
    private String type;
    private Map<String, String> params;
}
