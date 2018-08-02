package com.zlpy.Bean;

import java.util.List;

public class TopicShow {
private TopicBean topic;
private List<OptionsBean> list;
private List<SolutionBean> solut;

public List<SolutionBean> getSolut() {
	return solut;
}
public void setSolut(List<SolutionBean> solut) {
	this.solut = solut;
}
public TopicBean getTopic() {
	return topic;
}
public void setTopic(TopicBean topic) {
	this.topic = topic;
}
public List<OptionsBean> getList() {
	return list;
}
public void setList(List<OptionsBean> list) {
	this.list = list;
}


}
