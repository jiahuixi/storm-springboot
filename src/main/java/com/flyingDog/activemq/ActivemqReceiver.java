package com.flyingDog.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import java.util.ResourceBundle;

public class ActivemqReceiver {
    public static void main(String[] args) {
        String activeMqUrl= ResourceBundle.getBundle("config").getString("jms.url");
        String activeMqUsername = ResourceBundle.getBundle("config").getString("jms.username");
        String activeMqPassword = ResourceBundle.getBundle("config").getString("jms.password");
        String url= activeMqUrl+"?wireFormat.maxInactivityDuration=0";
        int i = 0;
        // ConnectionFactory ：连接工厂，JMS 用它创建连接
        ConnectionFactory connectionFactory;
        // Connection ：JMS 客户端到JMS Provider 的连接
        Connection connection = null;
        // Session： 一个发送或接收消息的线程
        Session session;
        // Destination ：消息的目的地;消息发送给谁.
        Destination destination;
        // 消费者，消息接收者
        MessageConsumer consumer;
        // connectionFactory = new ActiveMQConnectionFactory(
        // ActiveMQConnection.DEFAULT_USER,
        // ActiveMQConnection.DEFAULT_PASSWORD, "tcp://192.168.0.104:61616");
        connectionFactory = new ActiveMQConnectionFactory(activeMqUsername,
                activeMqPassword,
                url);
        try {
            // 构造从工厂得到连接对象
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            // 获取操作连接
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值 foo.bar 是一个服务器的queue，须在在ActiveMq的console配置
            destination = session.createQueue("EarlnWarn.bar");
            consumer = session.createConsumer(destination); // 创建消息消费者
            consumer.setMessageListener(new ActivemqListener());// 注册消息监听
            boolean flag = true;
			while(flag){
				Thread.sleep(1000);
			}
//			TextMessage message = (TextMessage) consumer.receive();
//			if (null != message) {
//				i++;
//				System.out.println("收到消息" + i +":"+ message.getText());
//			}
//			while (true) {
//				// 设置接收者接收消息的时间，为了便于测试，这里谁定为100s
//
//			}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection)
                    System.out.println("close----------------------------");
                    connection.close();
            } catch (Throwable ignore) {
            }
        }
    }

    public static void receiver() {
        // 新定义线程来处理接受
        new Thread() {
            @Override
            public void run() {
                try {
                    main(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }.start();
    }
}
