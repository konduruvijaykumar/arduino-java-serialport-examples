/**
 * 
 */
package org.pjay.serialportcommunicator;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vijay
 *
 */
public class SerialPortServlet extends HttpServlet{
	
	private static volatile boolean connected = false;
	
	/** Seial port */
	private static SerialPort serialPort;
	
	/** Streams */
	private static OutputStream serialOut;
	
	private static final int TIME_OUT = 2000;

	/**
	 * 
	 */
	private static final long serialVersionUID = -5816437600861387967L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<h1> GET No implemented</h1>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmdStr = req.getParameter("cmd");
		System.out.println("  ########### RQS: "+cmdStr);
		Commands cmd = Commands.valueOf(cmdStr);
		sendCmd(cmd);

		String nextJSP = "/index.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(req,resp);
	}

	private void sendCmd(Commands cmd) {
		if (connected) {
			synchronized (serialPort) {
				if (connected) {
					try {
						serialOut.write(cmd.toString().getBytes());
						serialOut.flush();
						System.out.println("  ########### SND: "+cmd.toString());
					} catch (IOException e) {
						e.printStackTrace();
						closePort();
					}
				}
			}
		}
	}

	@Override
	public void init() throws ServletException {
		try {
			initializePort("COM25", 9600);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		if(connected){
			closePort();
		}
	}

	private void closePort() {
		if(connected){
			serialPort.removeEventListener();
			serialPort.close();
			connected = false;
			System.out.println("  ########### CLOSE:");
		}
	}

	private void initializePort(String portName, int speed) throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException, IOException {
		if(!connected){
			CommPortIdentifier port = CommPortIdentifier.getPortIdentifier(portName); 
	        CommPort commPort = port.open(this.getClass().getName(),TIME_OUT);
	        serialPort = (SerialPort) commPort;
	        serialPort.setSerialPortParams(speed, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
	        serialOut=serialPort.getOutputStream();
			connected = true;
			System.out.println("  ########### OPEN:");
		}
	}
	
}
