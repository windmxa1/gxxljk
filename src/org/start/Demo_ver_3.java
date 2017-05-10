package org.start;

public class Demo_ver_3 {
	public static void main(String[] args) {
		MyThread thread1 = new MyThread("192.168.199.127", "6179");
		thread1.start();
//		MyThread thread2 = new MyThread("192.168.1.120", "6179");
//		thread2.start();
//		MyThread thread3 = new MyThread("localhost", "6179");
//		thread3.start();
//		MyThread thread4 = new MyThread("localhost", "6179");
//		thread4.start();
		// new Thread(thread4).start();

	} 
}
