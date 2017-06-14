package cn.com.daocaore.mongodb;

import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.junit.Test;

import qing.yun.hui.common.utils.BeanUtil;
import cn.com.daocaore.mongodb.sys.entity.SysUser;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;

/***
 *  <p>mongodb 单元测试</p>
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年6月15日上午9:57:16
 **/

public class TestMongodb {
	
	@Test
	public void mongodbTest1() {
		//@1.第一种方式
		try {
			// 连接到 mongodb 服务
			Mongo mongo = new Mongo("127.0.0.1", 27017);
			// 根据mongodb数据库的名称获取mongodb对象 ,
			DB db = mongo.getDB("mongo_demo");
			Set<String> collectionNames = db.getCollectionNames();
			// 打印出test中的集合
			for (String name : collectionNames) {
				System.out.println("collectionName===" + name);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void mongodbTest2(){
		//@2.第二种方式
		MongoClient mc=new MongoClient("127.0.0.1", 27017);
		MongoDatabase mgDatabase=mc.getDatabase("mongo_demo");
		MongoIterable<String> mongoIterable= mgDatabase.listCollectionNames();
		MongoCursor<String> mongoCursor= mongoIterable.iterator();
		while(mongoCursor.hasNext()){
			String name=mongoCursor.next();
			System.out.println("name:"+name);
		}
		mc.close();
	}
	
	@Test
	public void mongodbTestByFind(){
		//@2.第二种方式
		MongoClient mc=new MongoClient("127.0.0.1", 27017);
		MongoDatabase mgDatabase=mc.getDatabase("mongo_demo");
		MongoCollection<Document> mongoCollection= mgDatabase.getCollection("sys_user");
		FindIterable<Document>  findIterable= mongoCollection.find();	//查询所有 - find()
		MongoCursor<Document> mongoCursor= findIterable.iterator();
		while(mongoCursor.hasNext()){
			Document document= mongoCursor.next();
			System.out.println("document:"+document.toJson());
		}
		mc.close();
	}
	
	@Test
	public void mongodbTestByCondit(){
		//@4.第4种方式
		MongoClient mc=new MongoClient("127.0.0.1", 27017);
		MongoDatabase mgDatabase=mc.getDatabase("mongo_demo");
		MongoCollection<Document> mongoCollection= mgDatabase.getCollection("sys_user");
		FindIterable<Document> iter = mongoCollection.find(new Document("account_id",new Document("$eq",234997331)));
		// 或者　　FindIterable<Document> iter = doc.find(new Document("age",new Document("$eq",24)));
		//或者　　 FindIterable<Document> iter = doc.find(Filters.eq("name", "张三"));
		iter.forEach(new Block<Document>() {
			@Override
			public void apply(Document document) {
				System.out.println(document.toJson());
			}
		});
		mc.close();
	}
	
	@Test
	public void mongodbTestByUpdate(){
		//@4.第4种方式
		MongoClient mc=new MongoClient("127.0.0.1", 27017);
		MongoDatabase mgDatabase=mc.getDatabase("mongo_demo");
		MongoCollection<Document> mongoCollection= mgDatabase.getCollection("sys_user");
		Document filter=new Document("wechat","noGood");
		Document update=new Document("$set",new Document("wechat","xx-config"));
		UpdateResult result=mongoCollection.updateMany(filter, update);
//		UpdateResult result=mongoCollection.updateMany(new Document("wechat",new Document("$eq","good")),new Document("$set",new Document("wechat","noGood")));
		long count=result.getModifiedCount();
		System.out.println("count:"+count);
		mc.close();
	}
	
	@Test
	public void mongodbTestByUpdate2(){
		//@4.第4种方式
		MongoClient mc=new MongoClient("127.0.0.1", 27017);
		MongoDatabase mgDatabase=mc.getDatabase("mongo_demo");
		MongoCollection<Document> mongoCollection= mgDatabase.getCollection("sys_user");
		Document filter=new Document("_id","999888777good");
		SysUser user=new SysUser();
		user.setUserId("999888777good");
		user.setAge(44);
		user.setComments("is test");
		user.setWechat("myWechat");
		user.setEmail("myEmail@qq.com");
		Map<String,Object> map=BeanUtil.pojoToMap(user);
		Document update=new Document("$set",map);	//set多个字段....
		UpdateResult result=mongoCollection.updateMany(filter, update);
		long count=result.getModifiedCount();
		System.out.println("count:"+count);
		mc.close();
	}
	
}