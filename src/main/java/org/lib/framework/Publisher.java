package org.lib.framework;

import java.util.ArrayList;

public class Publisher<MsgType> {
	
	ArrayList<Subscriber<MsgType>> subs = new ArrayList<>();
	
	public Publisher() {
	}
	
	public void subscribe(Subscriber<MsgType> subscriber) {
		synchronized (subs) {
			subs.add(subscriber);
		}
	}
	
	public void unsubscribe(Subscriber<MsgType> subscriber) {
		synchronized (subs) {
			subs.remove(subscriber);
		}
	}
	
	protected void publish(MsgType msg) {
		synchronized (subs) {
			for ( Subscriber<MsgType> s : subs ) {
				s.receiveMessage(msg);
			}
		}
	}
	
	public interface Subscriber<MsgType> {
		void receiveMessage(MsgType msg);
	}
}
