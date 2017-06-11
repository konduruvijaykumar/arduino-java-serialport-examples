/**
 * 
 */
package org.pjay.serialportreading.arduino.savedata;

import org.hibernate.Session;

/**
 * @author Vijay
 *
 */
public class SensorDataDAOImpl {
	public static void saveSensorData(SensorData data) {
		Session session = HibernateUtils.getSessionFactory().openSession();//HibernateUtils.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(data);
		session.getTransaction().commit();
		session.close();
		// Error Due to using current session, please analyze later. (Looks like due to same entity object being present in 1st level cache and same session for hole save logic)
		// Hence moving to openSession() instead of current session. This same issue happens if you don't close the session obtained via openSession for each save 
		/**
			INFO: HHH000227: Running hbm2ddl schema export
			Hibernate: 
			    drop table if exists SENSOR_DATA
			Hibernate: 
			    create table SENSOR_DATA (
			        ID integer not null auto_increment,
			        HUMIDITY float,
			        READINGDATETIME datetime,
			        TEMPERATURE float,
			        primary key (ID)
			    )
			Dec 15, 2015 5:49:02 PM org.hibernate.tool.hbm2ddl.SchemaExport execute
			INFO: HHH000230: Schema export complete
			Hibernate: 
			    insert 
			    into
			        SENSOR_DATA
			        (HUMIDITY, READINGDATETIME, TEMPERATURE) 
			    values
			        (?, ?, ?)
			Dec 15, 2015 5:49:02 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl stop
			INFO: HHH000030: Cleaning up connection pool [jdbc:mysql://localhost:3306/sensors]
			Exception in thread "Thread-2" org.hibernate.service.UnknownServiceException: Unknown service requested [org.hibernate.engine.jdbc.connections.spi.ConnectionProvider]
				at org.hibernate.service.internal.AbstractServiceRegistryImpl.getService(AbstractServiceRegistryImpl.java:201)
				at org.hibernate.internal.AbstractSessionImpl.getJdbcConnectionAccess(AbstractSessionImpl.java:341)
				at org.hibernate.engine.jdbc.internal.JdbcCoordinatorImpl.<init>(JdbcCoordinatorImpl.java:114)
				at org.hibernate.engine.transaction.internal.TransactionCoordinatorImpl.<init>(TransactionCoordinatorImpl.java:89)
				at org.hibernate.internal.SessionImpl.<init>(SessionImpl.java:258)
				at org.hibernate.internal.SessionFactoryImpl$SessionBuilderImpl.openSession(SessionFactoryImpl.java:1589)
				at org.hibernate.context.internal.ThreadLocalSessionContext.buildOrObtainSession(ThreadLocalSessionContext.java:157)
				at org.hibernate.context.internal.ThreadLocalSessionContext.currentSession(ThreadLocalSessionContext.java:109)
				at org.hibernate.internal.SessionFactoryImpl.getCurrentSession(SessionFactoryImpl.java:1014)
				at org.pjay.serialportreading.arduino.savedata.SensorDataDAOImpl.saveSensorData(SensorDataDAOImpl.java:15)
				at org.pjay.serialportreading.arduino.savedata.ConsumerSlave.run(SerialListeningApp.java:179)
				at java.lang.Thread.run(Thread.java:744)
		 */
		
		// Reason or culprit for this issue was due to close of session factory in consumer thread finally block as shown below. Most probably above issue is not due to current session
		/**
			 finally {
				 // Close session factory
				 HibernateUtils.getSessionFactory().close();
			 }
		 */
	}
}
