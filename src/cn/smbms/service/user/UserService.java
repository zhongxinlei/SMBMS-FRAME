package cn.smbms.service.user;

import cn.smbms.pojo.User;

public interface UserService {
	
	public User login(String userCode,String password) throws Exception;

}
