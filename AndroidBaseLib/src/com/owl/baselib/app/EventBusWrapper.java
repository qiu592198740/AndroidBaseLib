package com.owl.baselib.app;

import de.greenrobot.event.EventBus;
/**
 * 事件总线管理包装类
 * @author qiushunming
 *
 */
public class EventBusWrapper {
	private EventBus mEventBus;
	
	private static EventBusWrapper instance;
	
	private EventBusWrapper(){
		mEventBus = EventBus.getDefault();
	}
	
	public static EventBusWrapper getInstance(){
		if (instance == null) {
			synchronized (EventBusWrapper.class) {
				if (instance == null) {
					instance = new EventBusWrapper();
				}
			}
		}
		return instance;
	}
	
	public void postEvent(Object event){
		mEventBus.post(event);
	}
	
	public void postStickyEvent(Object event){
		mEventBus.postSticky(event);
	}
	
	public void register(Object subscriber){
		mEventBus.register(subscriber);
	}
	
	public void register(Object subscriber, int priorty){
		mEventBus.register(subscriber, priorty);
	}
	
	public void registerSticky(Object subscriber){
		mEventBus.registerSticky(subscriber);
	}
	
	public void registerSticky(Object subscriber, int priorty){
		mEventBus.registerSticky(subscriber, priorty);
	}
	
	public <T> void getSticky(Class<T> eventType){
		mEventBus.getStickyEvent(eventType);
	}
	
	public void unregister(Object subscriber){
		mEventBus.unregister(subscriber);
	}
}
