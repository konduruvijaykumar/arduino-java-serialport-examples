/**
 * 
 */
package org.pjay.test;

/**
 * @author Vijay
 *
 */
public class ThreadJoinTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t1 = new Thread(){
			@Override
			public void run() {
				System.out.println(" :: Thread t1 IN :: ");
				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(" :: Thread t1 OUT :: ");
			}
		};
		
		Thread t2 = new Thread(){
			@Override
			public void run() {
				System.out.println(" :: Thread t2 IN :: ");
				try {
					Thread.sleep(30000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(" :: Thread t2 OUT :: ");
			}
		};
		
		t1.start();t2.start();
		
		try {
			System.out.println(" :: Before t1 Join :: ");
			t1.join();
			System.out.println(" :: After t1 Join :: ");
			System.out.println(" :: Before t2 Join :: ");
			t2.join();
			System.out.println(" :: After t2 Join :: ");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}

}
