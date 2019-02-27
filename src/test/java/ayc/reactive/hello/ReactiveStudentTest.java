package ayc.reactive.hello;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class ReactiveStudentTest {
	
	Student michael = new Student("Michael", "Peace");
    Student fiona = new Student("Fiona", "Tecso");
    Student sam = new Student("Sam", "Axe");
    Student jesse = new Student("Jesse", "Harper");
    
    @Test
    public void monoTests() {
    	//create new mono student
    	Mono<Student> monoSt = Mono.just(michael);
    	
    	//get student from mono publisher
    	Student st = monoSt.block();
    	
    	log.info(st.name());
    }
    
    @Test
    public void monoTransform() {
    	Mono<Student> monoSt = Mono.just(fiona);
    	
    	StudentCommand stC = monoSt.map(s -> {return new StudentCommand(s);})
    			.block();
    	
    	log.info(stC.name());
    }
    
    @Test(expected = NullPointerException.class)
    public void monoFilter() {
    	Mono<Student> monoSt = Mono.just(sam);
    	
    	Student st = monoSt.filter(s -> s.getFirstName().equalsIgnoreCase("aaaaa"))
    			.block();
    	
    	log.info(st.name());
    }
    
    @Test
    public void fluxTest() {
    	Flux<Student> fluxSt = Flux.just(michael, fiona, sam, jesse);
    	
    	fluxSt.subscribe(f -> log.info(f.name()));
    }
    
    @Test
    public void fluxTestFilter() {
    	Flux<Student> fluxSt = Flux.just(michael, fiona, sam, jesse);
    	
    	fluxSt.filter(s -> s.getFirstName().equals(fiona.getFirstName()))
    	.subscribe(ss -> log.info(ss.name()));
    	
    }
    
    @Test
    public void fluxTestDelayNoOutput() {
    	Flux<Student> fluxSt = Flux.just(michael, fiona, sam, jesse);
    	
    	fluxSt.delayElements(Duration.ofSeconds(1))
    	.subscribe(s -> log.info(s.name()));
    }
    
    @Test
    public void fluxTestDelay() throws InterruptedException {
    	CountDownLatch cdl = new CountDownLatch(1);
    	
    	Flux<Student> fluxSt = Flux.just(michael, fiona, sam, jesse);
    	
    	fluxSt.delayElements(Duration.ofSeconds(1))
    	.doOnComplete(cdl::countDown)
    	.subscribe(s -> log.info(s.name()));
    	
    	cdl.await();
    	
    }
    @Test
    public void fluxTestFilterDelay() throws InterruptedException {
    	CountDownLatch cdl = new CountDownLatch(1);
    	
    	Flux<Student> fluxSt = Flux.just(michael, fiona, sam, jesse);
    	fluxSt.delayElements(Duration.ofSeconds(1))
    	.filter(s -> s.getFirstName().contains("h"))
    	.doOnComplete(cdl::countDown)
    	.subscribe(s -> log.info(s.name()));
    	
    	cdl.await();
    }
    

}
