package kr.co.gudi.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class TestScheduler {
	
	// Autowired를 사용하거나 자기혼자 스스로 돌아야하는 경우에는 @Component를 붙여주어야 한다.
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	// 스케줄러는 프로그램과 생명주기를 함께한다. (켜질 때 같이 켜지고, 꺼질 때 같이 꺼진다.)
	// 각각의 메서드에 @Sheduled를 통해서 특정 주기마다 해당 메서드가 실행되도록 한다.
	
	/*@Scheduled(fixedDelay = 1000)
	public void fixedDelay() {
		logger.info("작업이 끝나고 1초후 실행");
		try {
			Thread.sleep(2000);   //0.5초씩 delay
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	
	@Scheduled(fixedRate = 1000)
	public void fixedRate() {
		logger.info("1초마다 실행 : ");
	}*/
	
	// 초 분 시 일 월 요일 년도(생략가능)
	// 매분 5초가 될 때 마다(지정) : 5
	// 0에서 5씩 증가할 때마다 수행 : 0/5
	// cron 또는 crontab으로 검색하면 사용법을 자세히 알 수 있다.
	@Scheduled(cron = "0/5 * * * * MON-FRI")  
	public void cron() {
		logger.info("5초마다 실행");
	}
	
}
