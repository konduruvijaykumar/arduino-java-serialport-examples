/**
 * 
 */
package org.pjay.serialportreading.arduino.savedata;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;


/**
 * @author Vijay
 * http://playground.arduino.cc/Interfacing/Java
 * https://www.youtube.com/watch?v=43Vdpz1YmdU
 * http://rxtx.qbang.org/wiki/index.php/Main_Page
 * http://fizzed.com/oss/rxtx-for-java
 * http://stackoverflow.com/questions/9044758/gnu-io-portinuseexception-unknown-application
 *
 */
public class SerialListeningApp implements SerialPortEventListener {

	// producer consumer related
	BlockingQueue<SensorData> blockingQueue = new LinkedBlockingDeque<>();
	final static SensorData POISON_PILL = new SensorData();
	
	SerialPort serialPort;
        /** The port we're normally going to use. */
	private static final String PORT_NAMES[] = { 
			"/dev/tty.usbserial-A9007UX1", // Mac OS X
                        "/dev/ttyACM0", // Raspberry Pi
			"/dev/ttyUSB0", // Linux
			"COM25", // Windows
	};
	/**
	* A BufferedReader which will be fed by a InputStreamReader 
	* converting the bytes into characters 
	* making the displayed results codepage independent
	*/
	private BufferedReader input;
	/** The output stream to the port */
	private OutputStream output;
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;

	public void initialize() {
                // the next line is for Raspberry Pi and 
                // gets us into the while loop and was suggested here was suggested http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
                // System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");

		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		//First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	/**
	 * This should be called when you stop using the port.
	 * This will prevent port locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine=input.readLine();
				//System.out.println(inputLine);
				String[] strArr = inputLine.split(",");
				// This is the producer of data
				blockingQueue.put(new SensorData(Float.parseFloat(strArr[0]), Float.parseFloat(strArr[1]), new Date()));
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		// Ignore all the other eventTypes, but you should consider the other ones.
	}

	public static void main(String[] args) throws Exception {
		SerialListeningApp main = new SerialListeningApp();
		main.initialize();
/*		Thread t=new Thread() {
			public void run() {
				//the following line will keep this app alive for 1000 seconds,
				//waiting for events to occur and responding to them (printing incoming messages to console).
				//try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
				try {Thread.sleep(300000);} catch (InterruptedException ie) {}
			}
		};*/
		Thread t = new Thread(new PoisonPillInjector(main.blockingQueue));
		Thread t1 = new Thread(new ConsumerSlave(main.blockingQueue));
		t.start();
		t1.start();
		System.out.println("Started");
		// Not sure if this is required
		try {
			// We throw exception so we don't need handle exception
			t.join();
			main.close();
			t1.join();
		} catch (InterruptedException e) {
			System.out.println("Threads are interrupted during join function call");
		}
	}
}

class ConsumerSlave implements Runnable{
	
	BlockingQueue<SensorData> blockingQueue;

	public ConsumerSlave(BlockingQueue<SensorData> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}
	
	@Override
	public void run() {
		// Dealying initially
		try {Thread.sleep(4000);} catch (InterruptedException ie) {}
		for(;;){
			try {
				SensorData data = blockingQueue.take();
				if(SerialListeningApp.POISON_PILL.equals(data)){
					break;
				}
				//System.out.println("Data is :: " + data);
				// Save to DB
				SensorDataDAOImpl.saveSensorData(data);
			} catch (InterruptedException e) {
				System.out.println("Error taking from queue");
				e.printStackTrace();
			}
		}
		HibernateUtils.getSessionFactory().close();
	}
}

class PoisonPillInjector implements Runnable{
	
	BlockingQueue<SensorData> blockingQueue;
	
	public PoisonPillInjector(BlockingQueue<SensorData> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		// Will keep this app alive for 300 seconds i.e., 5 mins
		//try {Thread.sleep(300000);} catch (InterruptedException ie) {}
		try {Thread.sleep(300000);} catch (InterruptedException ie) {}
		try {
			blockingQueue.put(SerialListeningApp.POISON_PILL);
		} catch (InterruptedException e) {
			System.out.println("Error adding poison pill to queue");
			e.printStackTrace();
		}
	}
	
}