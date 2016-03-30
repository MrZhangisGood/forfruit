package com.forjob.core.redis;

import com.forjob.core.util.JsonUtil;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.*;

@SuppressWarnings("unchecked")
public class RedisTool {

    private static final Logger logger = Logger.getLogger(RedisTool.class);

	private static JedisPool pool = null;

	/**
	 * 构建redis连接池
     * @author zhanglm@joyplus.com.cn
	 * @return JedisPool
	 */
	private static JedisPool getPool() {
		if (pool == null) {
            try
            {
                Properties props = new Properties();
                props.load(RedisTool.class.getClassLoader().getResourceAsStream("redis.properties"));

                JedisPoolConfig config = new JedisPoolConfig();

                config.setMaxActive(Integer.valueOf(props.getProperty("jedis.pool.maxActive")).intValue());
                config.setMaxIdle(Integer.valueOf(props.getProperty("jedis.pool.maxIdle")).intValue());
                config.setMaxWait(Long.valueOf(props.getProperty("jedis.pool.maxWait")).longValue());
                config.setTestOnBorrow(Boolean.valueOf(props.getProperty("jedis.pool.testOnBorrow")).booleanValue());
                config.setTestOnReturn(Boolean.valueOf(props.getProperty("jedis.pool.testOnReturn")).booleanValue());

                pool = new JedisPool(config, props.getProperty("redis.ip"), Integer.valueOf(props.getProperty("redis.port")).intValue(), 6000);

                logger.info("Redis Config: server={"+props.getProperty("redis.ip")+"},port={"+props.getProperty("redis.port")+"}");
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
		return pool;
	}

	/**
	 * 返还到连接池
     * @author zhanglm@joyplus.com.cn
	 * @param pool
	 * @param redis
	 */
	public static void returnResource(JedisPool pool, Jedis redis) {
		if (redis != null) {
			pool.returnResource(redis);
		}
	}

	/**
	 * 获取数据
     * @author zhanglm@joyplus.com.cn
	 * @param key
	 * @return
	 */
	public static String get(String key){
		String value = null;
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			value = jedis.get(key);
		} catch (Exception e) {
			//释放redis对象
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			//返还到连接池
			returnResource(pool, jedis);
		}
		return value;
	}

	/**
	 * 缓存
     * @author zhanglm@joyplus.com.cn
	 * @param key
	 * @return
	 */
	public static void set(String key, String value){
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			jedis.set(key, value);
		} catch (Exception e) {
			//释放redis对象
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			//返还到连接池
			returnResource(pool, jedis);
		}
	}

    /**
     * 获取数据
     * @author zhanglm@joyplus.com.cn
     * @param key
     * @return
     */
    public static <T> T getObjct(String key, Class<T> clazz){
        T value = null;
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            String redisJson = jedis.get(key);
            value = (T) JsonUtil.json2Object(redisJson, clazz);
        } catch (Exception e) {
            //释放redis对象
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //返还到连接池
            returnResource(pool, jedis);
        }
        return value;
    }

	/**
	 * 缓存
     * @author zhanglm@joyplus.com.cn
	 * @param key
	 * @return
	 */
	public static void setObject(String key, Object value){
		JedisPool pool = null;
		Jedis jedis = null;
		try {
			pool = getPool();
			jedis = pool.getResource();
			jedis.set(key, JsonUtil.object2Json(value));
            jedis.set()
		} catch (Exception e) {
			//释放redis对象
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			//返还到连接池
			returnResource(pool, jedis);
		}
	}


    /********************************************************************/
    /** TEST 使用                                                       */
    /** @author zhanglm@joyplus.com.cn                                 */
    /** @return                                                        */
    /********************************************************************/
    public static void main(String[] args){
        System.out.println("/**************************.TEST START");
        Jedis jedis = RedisTool.getPool().getResource();
        //TEST String
        RedisTool.set("KEY.STRING", "My name is zhanglm,I'm a boy. I love my girlfriend,She is a beautiful girl,She name is Huangfl.");
        //TEST Map
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "zhanglm");
        map.put("age", "21");
        RedisTool.setObject("KEY.OBJECT.MAP", map);
        //TEST List
        List<Map> list = new ArrayList<Map>();
        list.add(map);
        RedisTool.setObject("KEY.OBJECT.LIST", list);
        //TEST List & Map
        List<Object> list_Map = new ArrayList<Object>();
        list_Map.add(map);
        list_Map.add("List Type");
        RedisTool.setObject("KEY.OBJECT.LIST_MAP", list_Map);

        Map<String, String> getMap = RedisTool.getObjct("KEY.OBJECT.MAP", HashMap.class);
        System.out.println("getMap.name:"+getMap.get("name"));

        List<HashMap> getList = RedisTool.getObjct("KEY.OBJECT.LIST", ArrayList.class);
        for (Map obj : getList){
            System.out.println("getList.Map.name:"+obj.get("name"));
            System.out.println("getList.Map.age:"+obj.get("age"));
        }

        List<Object> getList_Map = RedisTool.getObjct("KEY.OBJECT.LIST_MAP", ArrayList.class);
        for (Object obj : getList_Map){
            if(obj instanceof Map){
                Map<String, String> m = (Map<String, String>) obj;
                System.out.println("getList.Map.name:"+m.get("name"));
                System.out.println("getList.Map.age:"+m.get("age"));
            }else{
                System.out.println("getList:"+obj);
            }
        }

        System.out.println("/**************************.TEST END");
    }

    /********************************************************************/
    /** TEST 测试性能                                                       */
    /** @author zhanglm@joyplus.com.cn                                 */
    /** @return                                                        */
    /********************************************************************/
    public static void main_(String[] args){
        System.out.println("/**************************.TEST START");

        RedisTool.set("KEY.STRING", "My name is zhanglm,I'm a boy. I love my girlfriend,She is a beautiful girl,She name is Huangfl.");

        for(int i=0;i<100;i++){
            new Thread(new Runnable(){
                @Override
                public void run() {
                    while (true){
                        String value = RedisTool.get("KEY.STRING");
                        System.out.println("Thread Id : " +Thread.currentThread().getId()+ " Value : " + value);
                    }
                }
            }).start();
        }

        /*for(int i=0;i<100;i++){
            new Thread(new Runnable(){
                @Override
                public void run() {
                    while (true){
                        Jedis jedis = new Jedis("120.27.113.67", 6379);
                        String value = jedis.get("KEY.STRING");
                        System.out.println("Thread Id : " +Thread.currentThread().getId()+ " Value : " + value);
                    }
                }
            }).start();
        }*/

        System.out.println("/**************************.TEST END");
    }

}
