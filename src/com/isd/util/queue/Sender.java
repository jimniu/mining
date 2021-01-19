package com.isd.util.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

import com.isd.util.C;

import net.sf.json.JSONObject;

/**
 * Created by Jim on 2017/2/3.
 */
public class Sender {

    public static void send(Queue queue){
        // TODO Auto-generated method stub
        ConnectionFactory connectionFactory; // Connection ：JMS 客户端到JMS
        // Provider 的连接
        Connection connection = null; // Session： 一个发送或接收消息的线程
        Session session; // Destination ：消息的目的地;消息发送给谁.
        Destination destination; // MessageProducer：消息发送者
        MessageProducer producer; // TextMessage message;

        String key = "queue_message";
        //从应用的全局变量提取
        Object obj = C.getFromApplication(key);
        if(obj == null){
            // 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
            connectionFactory = new ActiveMQConnectionFactory(
                    ActiveMQConnection.DEFAULT_USER,
                    ActiveMQConnection.DEFAULT_PASSWORD, "tcp://master.dig88.cn:61616");

            C.saveToApplication(key, connectionFactory);
        }else{
            connectionFactory = (ConnectionFactory)obj;
        }

        try { // 构造从工厂得到连接对象
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            // 获取操作连接
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
            destination = session.createQueue("mining");
            // 得到消息生成者【发送者】
            producer = session.createProducer(destination);
            // 设置持久化
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            //对象转成JSON
            JSONObject jsonObject = JSONObject.fromObject(queue);

            ActiveMQTextMessage msg = (ActiveMQTextMessage) session.createTextMessage(jsonObject.toString());
            producer.send(msg);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (Throwable ignore) {
            }
        }
    }

    public static void task(String taskType, Object value){
        // TODO Auto-generated method stub
        ConnectionFactory connectionFactory; // Connection ：JMS 客户端到JMS
        // Provider 的连接
        Connection connection = null; // Session： 一个发送或接收消息的线程
        Session session; // Destination ：消息的目的地;消息发送给谁.
        Destination destination; // MessageProducer：消息发送者
        MessageProducer producer; // TextMessage message;

        String key = "queue_message";
        //从应用的全局变量提取
        Object obj = C.getFromApplication(key);
        if(obj == null){
            // 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
            connectionFactory = new ActiveMQConnectionFactory(
                    ActiveMQConnection.DEFAULT_USER,
                    ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");

            C.saveToApplication(key, connectionFactory);
        }else{
            connectionFactory = (ConnectionFactory)obj;
        }

        try { // 构造从工厂得到连接对象
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            // 获取操作连接
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
            destination = session.createQueue("task");
            // 得到消息生成者【发送者】
            producer = session.createProducer(destination);
            // 设置持久化
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            //组合出消息的内容，为 消息类型 = 值 的形式
            String message = taskType + "=" + value.toString();

            ActiveMQTextMessage msg = (ActiveMQTextMessage) session.createTextMessage(message);
            producer.send(msg);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (Throwable ignore) {
            }
        }
    }

}
