package productionMq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/***
 ** @category 消息的生产者（发送者）
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月29日上午10:27:58
 **/
public class ProductionMQ {
	
	
	//mq的默认用户名
	public static final String USER_NAME=ActiveMQConnection.DEFAULT_USER;
	
	//mq的默认密码
	public static final String USER_PWD=ActiveMQConnection.DEFAULT_PASSWORD;
	
	//mq的默认连接
	public static final String URL=ActiveMQConnection.DEFAULT_BROKER_URL;
	
	//发送消息的数量 
	private static final int SENDNUM = 10;
	
	public static void main(String[] args){
		//ConnectionFactory 工厂
		ConnectionFactory conFactory;
		Connection connection = null;
		Session session;
		/**
		 * Destination的意思是消息生产者的消息发送目标或者说消息消费者的消息来源。对于消息生产者来说，
		 *  它的Destination是某个队列（Queue）或某个主题（Topic）;对于消息消费者来说，它的Destination也是某个队列或主题（即消息来源）。
		 *	所以，Destination实际上就是两种类型的对象：Queue、Topic可以通过JNDI来查找Destination。
		 * */
		Destination destination=null;
		conFactory=new ActiveMQConnectionFactory(USER_NAME, USER_PWD, "tcp://192.168.1.77:61616");
		MessageProducer messageProducer=null;
		try {
			//通过连接工厂获取连接
			connection=conFactory.createConnection();
			//启动
			connection.start();
			//创建session
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            //创建一个名称为HelloWorld的消息队列
            destination=session.createQueue("HelloWord");
            //创建消息生产者
            messageProducer = session.createProducer(destination);
            //发送消息
            sendMessage(session, messageProducer);
            session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(null!=connection){
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 发送消息
	 * */
	public static void sendMessage(Session session,MessageProducer messageProducer) throws JMSException{
		for(int i=0;i<SENDNUM;i++){
			TextMessage textMsg=session.createTextMessage();
			textMsg.setText("hello word"+ i);
			messageProducer.send(textMsg);
		}
	}
}
