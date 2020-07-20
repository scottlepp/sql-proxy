package jdbc;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Map;

@Getter
@Setter
@Service
public class Connector {

    @Autowired
    private DatabaseLookup databaseLookup;
    private DataSource dataSource;

    public void connect(Connection conn) throws SQLException {
        dataSource = init(conn);
        dataSource.getConnection();
    }

    private DataSource init(Connection conn) {
        DataSourceBuilder builder = getBuilder(conn);
        builder.username(conn.getUsername());
        builder.password(conn.getPassword());
        return builder.build();
    }

    private DataSourceBuilder getBuilder(Connection conn) {
        DataSourceBuilder builder = DataSourceBuilder.create();
        String type = conn.getType().toLowerCase();
        Map<String, String> dbSettings = databaseLookup.get(type);
        System.out.println(dbSettings);
        if (dbSettings != null) {
            builder.driverClassName(dbSettings.get("driver"));
            String urlTemplate = dbSettings.get("url");
            String url = MessageFormat.format(urlTemplate, conn.getHost(), conn.getPort(), conn.getDatabase());
            if (conn.getPort() == "") {
                url = url.replace(conn.getHost() + ':', conn.getHost());
            }
            url = url.replace("{3}", conn.getUsername());
            url = url.replace("{4}", conn.getPassword());
            System.out.println(url);
            builder.url(url);
        } else {
            throw new RuntimeException("Database type  " + type + " not supported");
        }
        return builder;
    }
}
