package javacore.net;

import java.util.HashMap;

public class ClientMap<K, V> extends HashMap<K, V> {

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		for(V val:values()){
			if(val.equals(value) && val.hashCode() == value.hashCode())
				throw new RuntimeException("error!");
		}
		return super.put(key, value);
	}
	
	public K getKeyByValue(V val){
		for(K key: keySet()){
			if(get(key).equals(val) &&get(key) == val){
				return key;
			}
		}
		return null;
	}
	
	public void removeByValue(Object value){
		for(Object key:keySet()){
			if(get(key) == value){
				remove(key);
				break;
			}
		}
	}

}
