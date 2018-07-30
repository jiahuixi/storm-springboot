package hytera.storm.storm.spout;

import java.util.List;
import java.util.Map;

import hytera.storm.model.User;
import hytera.storm.service.UserService;
import hytera.storm.util.GetSpringBean;
import org.apache.storm.shade.org.json.simple.JSONArray;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

public class TestSpout extends BaseRichSpout {
	List<Map<String, Object>> mapType;
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private SpoutOutputCollector collector;

	@Override
	public void nextTuple() {
		try {
			Thread.sleep(1000);
			User user = userService.selectByPrimaryKey(1);
				collector.emit(new Values(user));
                //collector.emit(new Values("123456"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector arg2) {
		try {
			userService= GetSpringBean.getBean(UserService.class);
			collector = arg2;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {

		arg0.declare(new Fields("str"));
	}
}