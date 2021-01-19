package com.isd.util;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

public class CacheManager {
	private static CacheManager instance;
	private static Object lock = new Object();
	private GeneralCacheAdministrator admin;

	private CacheManager() {
		admin = new GeneralCacheAdministrator();
	}

	public static CacheManager getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new CacheManager();
				}
			}
		}
		return instance;
	}

    //添加被缓存的对象;   
    public void putInCache(String key,Object value){   
        admin.putInCache(key,value);   
    }   

    public void putInCache(String key,Object value, String[] groups){   
        admin.putInCache(key,value, groups);   
    }   

    //删除被缓存的对象;   
    public void flushEntry(String key){   
        admin.flushEntry(key);   
    }   

    //删除被缓存的Group   
    public void flushGroup(String group){   
        admin.flushGroup(group);   
    }
    
    //删除所有被缓存的对象;
    public void flushAll(){   
        admin.flushAll();
    }          

    public void cancelUpdate(String key){   
        admin.cancelUpdate(key);
    }          

    //获取被缓存的对象;   

    public Object getFromCache(String key, int refreshPeriod) throws NeedsRefreshException{   
    	return admin.getFromCache(key,refreshPeriod);   
    }           

    public Object getFromCache(String key) throws NeedsRefreshException{   
    	return admin.getFromCache(key);   
    }     
}
