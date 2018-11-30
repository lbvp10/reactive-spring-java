package com.reactiva.demo;

import java.util.List;

public interface TransaccionalBatch <T> {
	
	void saveAll(List<T> objects);
	
	void processSaveBatch(List<T> subList);

}
