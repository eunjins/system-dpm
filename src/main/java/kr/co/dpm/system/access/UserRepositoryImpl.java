package kr.co.dpm.system.access;

import org.apache.ibatis.io.Resources;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Value("${accessPath}")
    private String filePath;

    @Override
    public User select() throws IOException {
        User user = new User();

        Properties properties = new Properties();
        InputStream inputStream = Resources.getResourceAsStream(filePath);
        properties.load(inputStream);

        user.setId(properties.getProperty("id"));
        user.setPassword(properties.getProperty("password"));

        return user;
    }
}
