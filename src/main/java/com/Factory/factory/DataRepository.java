package com.Factory.factory;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

//mongodb 接口
public interface DataRepository extends MongoRepository<RecvData,String>{
	RecvData findByFactoryName(String factoryName);
	public void initDB(); 
	public void saveData(RecvData data);
	public List<RecvData> findAll();
	public List<RecvData> findAllbetween(String fieldname,String starvalue,String endvalue);
	public void updateOneData(RecvData user);
	public void updateAllData(String factoryname,String opcaddress,String groupName,String item,String time,String values);
	public void updateAllData(String  factoryname,String values);
	public List<RecvData> findAllByname(String fieldname,String value);
	public void deleteBygroupName(String fieldname ,String value);
	public void delete(RecvData data);
	public List<RecvData> findAllBytime(long begin,long end);
	public List<RecvData> findAllByIdandTime(String section,String value,long begin,long end);
}
