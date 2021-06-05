package nikhil.nani.metrics.recorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MetricsRecorderApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(MetricsRecorderApplication.class, args);
    }
}
