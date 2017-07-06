package com.Factory.factory;

import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
//mongodb操作实现
//@RestController
//@Component
@Import(MongoConfig.class)
@Component
public class DataRepositoryImpl {//{
	
	@Resource
	private MongoTemplate mongoTemplate; 
	@Resource
	private String userUri;

	private static Logger logger = Logger.getLogger(DataRepositoryImpl.class); 
	
	//重新建立连接
	private boolean getNewMongoConnection(){
		//mongodb://root:123456@localhost:27017/factory
		//"mongodb://username:pwd@服务器地址:端口/?authMechanism=SCRAM-SHA-1"
		//mongodb://root:123456@localhost:27017/factory?authMechanism=SCRAM-SHA-1") ;
		MongoClientURI uri = new MongoClientURI(userUri+"?authMechanism=SCRAM-SHA-1");	
		MongoClient mongoClient = new MongoClient(uri);
		mongoTemplate = new MongoTemplate(mongoClient, "pbgs_core");
		if (mongoTemplate.getDb().getName().isEmpty())
			return false;
		else {
			return true;
		}
	}
	//确认数据库存在
	public void initDB() {
		try {
			if (null==mongoTemplate || mongoTemplate.getDb().getName().isEmpty()) {
				if (false==getNewMongoConnection()){
					throw new FactorException("Mongodb can NOT connect !");
				}
			}
		} catch (FactorException e) {
			logger.error(e.getMessage());
		}
	}
	//保存
    public void saveData(RecvData data) {
    	mongoTemplate.save(data);
        //dataRepository.save(data);
    }
    //@Override
	public List<RecvData> findAll() {
		return mongoTemplate.findAll(RecvData.class);
	}
	public List<RecvData> findAllBytime(long begin,long end){
		Query query=new Query(Criteria.where("time").gte(begin).lte(end));
        List<RecvData> user =  mongoTemplate.find(query,RecvData.class);
        return user;
	}
	public List<RecvData> findAllByIdandTime(String section,String value,long begin,long end){
		Query query=new Query(Criteria.where(section).is(value).and("time").gte(begin).lte(end));
        List<RecvData> user =  mongoTemplate.find(query,RecvData.class);
        return user;
	}

	//@Override
	public RecvData findByFactoryName(String factoryName) {
		Query query = new Query(Criteria.where("factoryName").is(factoryName));
		return (RecvData) mongoTemplate.findOne(query, RecvData.class);
	}
    //删除记录
    public void delete(RecvData data){
    	mongoTemplate.remove(data);
    }
    public void deleteBygroupName(String section ,String value){
    	Query query = Query.query(Criteria.where(section).is(value));
    	mongoTemplate.findAllAndRemove(query,RecvData.class );
    }
    public void deleteUserById(String fieldname,String value) {
        Query query=new Query(Criteria.where(fieldname).is(value));
        mongoTemplate.remove(query,RecvData.class);
    }
    //按名称查询
	public List<RecvData> findAllByname(String fieldname,String value){
        Query query=new Query(Criteria.where(fieldname).is(value));
        List<RecvData> user =  mongoTemplate.find(query,RecvData.class);
        return user;
    }
	public List<RecvData> findAllbetween(String fieldname,String starvalue,String endvalue){
        Query query=new Query(Criteria.where(fieldname).gte(starvalue).lte(endvalue));
        List<RecvData> user =  mongoTemplate.find(query,RecvData.class);
        return user;
    }
    public void updateOneData(RecvData user) {
        Query query=new Query(Criteria.where("ID").is(user.getid()));
        Update update= new Update().set("factoryName", user.getfactoryName()).set("opcAddress", user.getopcAddress()).
        		set("groupName",user.getgroupName()).set("itemName",user.getitemName()).
        		set("time",user.gettime()).set("valueType",user.getvalueType()).set("value", user.getvalue());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query,update,RecvData.class);
        //更新查询返回结果集的所有
         //mongoTemplate.updateMulti(query,update,Data.class);
    }
    public void updateAllData(String factoryname,String opcaddress,String groupName,String item,String time,String values) {
        Query query=new Query(Criteria.where("factoryName").is(factoryname).
        		and("opcAddress").is(opcaddress).and("groupName").is(groupName).and("itemName").is(item));
        Update update= new Update().set("factoryName", factoryname).set("opcAddress", opcaddress).
        		set("groupName",groupName).set("itemName",item).
        		set("time",time).set("value",values);
        //更新查询返回结果集的所有
         mongoTemplate.updateMulti(query,update,RecvData.class);
    }
    public void updateAllData(String  factoryname,String values) {
        Query query=new Query(Criteria.where("factoryname").is(factoryname));
        Update update= new Update().set("factoryname", factoryname).set("value", values);
        //更新查询返回结果集的第一条
        //mongoTemplate.updateFirst(query,update,Data.class);
        //更新查询返回结果集的所有
         mongoTemplate.updateMulti(query,update,RecvData.class);
    }
  //part implementation
    /*
  	@Override
  	public List<RecvData> findAll(Sort arg0) {
  		return mongoTemplate.findAll(RecvData.class);
  	}

  	@Override
  	public <S extends RecvData> List<S> findAll(Example<S> arg0) {
  		// TODO Auto-generated method stub
  		return null;
  	}

  	@Override
  	public <S extends RecvData> List<S> findAll(Example<S> arg0, Sort arg1) {
  		// TODO Auto-generated method stub
  		return null;
  	}

  	@Override
  	public <S extends RecvData> S insert(S arg0) {
  		// TODO Auto-generated method stub
  		return null;
  	}

  	@Override
  	public <S extends RecvData> List<S> insert(Iterable<S> arg0) {
  		// TODO Auto-generated method stub
  		return null;
  	}

  	@Override
  	public <S extends RecvData> List<S> saveAll(Iterable<S> arg0) {
  		// TODO Auto-generated method stub
  		return null;
  	}

  	@Override
  	public Page<RecvData> findAll(Pageable arg0) {
  		// TODO Auto-generated method stub
  		return null;
  	}

  	@Override
  	public long count() {
  		// TODO Auto-generated method stub
  		return 0;
  	}

  	@Override
  	public void delete(RecvData arg0) {
  		// TODO Auto-generated method stub
  		
  	}

  	@Override
  	public void deleteAll() {
  		// TODO Auto-generated method stub
  		
  	}

  	@Override
  	public void deleteAll(Iterable<? extends RecvData> arg0) {
  		// TODO Auto-generated method stub
  		
  	}

  	@Override
  	public void deleteById(String arg0) {
  		// TODO Auto-generated method stub
  		
  	}

  	@Override
  	public boolean existsById(String arg0) {
  		// TODO Auto-generated method stub
  		return false;
  	}

  	@Override
  	public Iterable<RecvData> findAllById(Iterable<String> arg0) {
  		// TODO Auto-generated method stub
  		return null;
  	}

  	@Override
  	public Optional<RecvData> findById(String arg0) {
  		// TODO Auto-generated method stub
  		return null;
  	}

  	@Override
  	public <S extends RecvData> S save(S arg0) {
  		// TODO Auto-generated method stub
  		return null;
  	}

  	@Override
  	public <S extends RecvData> long count(Example<S> arg0) {
  		// TODO Auto-generated method stub
  		return 0;
  	}

  	@Override
  	public <S extends RecvData> boolean exists(Example<S> arg0) {
  		// TODO Auto-generated method stub
  		return false;
  	}

  	@Override
  	public <S extends RecvData> Page<S> findAll(Example<S> arg0, Pageable arg1) {
  		// TODO Auto-generated method stub
  		return null;
  	}

  	@Override
  	public <S extends RecvData> Optional<S> findOne(Example<S> arg0) {
  		// TODO Auto-generated method stub
  		return null;
  	}
	*/
	

	
}
