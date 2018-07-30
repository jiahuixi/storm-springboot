package hytera.storm.activemq;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ActivemqListener implements MessageListener {
    static boolean nextTupleFlag = false;
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("收到MQ消息：" + ((TextMessage)message).getText());
            if(((TextMessage)message).getText().equals("newCaseInsert")){
                getNextTupleTrue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean getNextTupleFalse(){
        nextTupleFlag = false;
        return nextTupleFlag;
    }
    public static boolean getNextTupleTrue(){
        nextTupleFlag = true;
        return nextTupleFlag;
    }
}
