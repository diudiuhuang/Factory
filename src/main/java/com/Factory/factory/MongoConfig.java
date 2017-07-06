package com.Factory.factory;

import java.net.UnknownHostException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import com.mongodb.MongoClientURI;

//mongodb配置
@Configuration 
public class MongoConfig {
		//@Value("${mongo.host}")
	   @Value("${spring.data.mongodb.uri}")  
	   private  String MONGODB_URI;  
	  
	   @Bean
	   public String getUserMongoDBuri(){
		   return MONGODB_URI;
	   }
	   /*
	   public MongoTemplate getMongoTemplate(){
		   return new MongoTemplate(new SimpleMongoDbFactory(
				   new MongoClientURI(MONGODB_URI))); 
	   }
	   */
	   //@Bean  
	   public MongoMappingContext mongoMappingContext() {  
	      MongoMappingContext mappingContext = new MongoMappingContext();  
	      return mappingContext;  
	   }  
	   // ==================== 连接到 mongodb1 服务器 ======================================  
	   //@Bean //使用自定义的typeMapper去除写入mongodb时的“_class”字段  
	   public MappingMongoConverter mappingMongoConverter() throws Exception {  
	      DefaultDbRefResolver dbRefResolver = new DefaultDbRefResolver(this.dbFactory());  
	      MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, this.mongoMappingContext());  
	      converter.setTypeMapper(new DefaultMongoTypeMapper(null));  
	      return converter;  
	   }  
	  
	   //@Bean  
	   public MongoDbFactory dbFactory() throws UnknownHostException {  
	      return new SimpleMongoDbFactory(new MongoClientURI(MONGODB_URI));  
	   } 
	   @Bean
	   public MongoTemplate mongoTemplate() throws Exception {  
	      return new MongoTemplate(this.dbFactory(), this.mappingMongoConverter());  
	   } 
} 
