package kr.pe.goodwilldd.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:root-context.xml")
public class HelloWorldTest {

	// @Autowired
	// @Qualifier("messageChannel")
	// MessageChannel messageChannel;

	@Autowired
	Greeter greeter;

	// @Test
	// public void testHelloWorld() {
	// Message<String> helloWorld = new GenericMessage<String>("Hello World");
	// messageChannel.send(helloWorld);
	// }

	@Test
	public void testHelloWorld2() {
		System.out.println("Started..");
		long start = System.nanoTime();
		for (int i = 0; i < 10; i++) {
			this.greeter.sendGreeting(String.format("Hello World %d", i));
		}
		System.out.println("Completed..");
		System.out.println(String.format("Took %f ms",
				(System.nanoTime() - start) / 10e6));
	}
}