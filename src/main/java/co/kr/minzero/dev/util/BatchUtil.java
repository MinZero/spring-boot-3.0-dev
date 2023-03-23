package co.kr.minzero.dev.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@EnableAsync
@Slf4j
public class BatchUtil {

    @Async
    @Scheduled(cron = "0 0/5 * * * ?")
    public void batchTest() {
        log.info("Batch Test - " + new Date());
    }
}
