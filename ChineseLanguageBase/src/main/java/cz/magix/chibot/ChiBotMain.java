package cz.magix.chibot;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ChiBotMain {

	public ChiBotMain() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		applicationContext.close();
	}

	public static void main(String[] args) {
		new ChiBotMain();
	}
}
