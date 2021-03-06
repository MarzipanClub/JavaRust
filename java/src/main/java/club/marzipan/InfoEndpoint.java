package club.marzipan;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoEndpoint {

    @Autowired
    BuildProperties buildProperties;

    private Map<String, String> info;

    @PostConstruct
    public void init() throws IOException {
        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("git.properties"));
        info = new HashMap<>();
        info.put("appName", buildProperties.getArtifact());
        info.put("version", String.valueOf(properties.get("git.build.version")));
        info.put("gitBranch", String.valueOf(properties.get("git.branch")));
        info.put("gitCommitHash", String.valueOf(properties.get("git.commit.id")));
        info.put("buildTimeUtc", buildProperties.get("time"));
    }

    @GetMapping("/info")
    public ResponseEntity<Map<String, String>> get_info() {
        return new ResponseEntity<>(info, HttpStatus.OK);
    }

}
