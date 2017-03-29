package consumeMq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/***
 ** @category 消息消费者
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月29日上午10:47:03
 **/
public class ConsumeMQ {
	//秋涛路572号，邮政速递物流
	
	//mq的默认用户名
	public static final String USER_NAME=ActiveMQConnection.DEFAULT_USER;
	
	//mq的默认密码
	public static final String USER_PWD=ActiveMQConnection.DEFAULT_PASSWORD;
	
	//mq的默认连接
	public static final String URL=ActiveMQConnection.DEFAULT_BROKER_URL;
	
	//发送消息的数量 
	private static final int SENDNUM = 10;
	
	public static void main(String[] args){
		ConnectionFactory conFactory=null;
		Connection conn=null;
		Session session=null;
		Destination destionation=null;
		MessageConsumer messageConsumer;//消息的消费者
        //实例化连接工厂
		conFactory = new ActiveMQConnectionFactory(ConsumeMQ.USER_NAME, ConsumeMQ.USER_PWD, ConsumeMQ.URL);
		try {
			conn=conFactory.createConnection();
			conn.start();
			session=conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destionation=session.createQueue("HelloWord");
			//创建消息消费者
            messageConsumer = session.createConsumer(destionation);
            while (true) {
                TextMessage textMessage = (TextMessage) messageConsumer.receive(SENDNUM);
                if(textMessage != null){
                    System.out.println("收到的消息:" + textMessage.getText());
                }else {
                    break;
                }
            }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(null!=conn){
				try {
					conn.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
