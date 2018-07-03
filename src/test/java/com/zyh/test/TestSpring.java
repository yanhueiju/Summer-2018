package com.zyh.test;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext*.xml")
public class TestSpring {

	@Autowired
	private InfoMapper infoMapper;
//	private UserMapper userMapper;
	
	@Test
	public void testselect() {
		HashMap<String, String> infos = new HashMap<>();
		infos.put("info_category", "ALL");
		infos.put("begin", "2018-06-19");
		infos.put("end", "2018-07-03");
		infos.put("info_title", "%"+"t"+"%");

		List<Info> info = infoMapper.fuzzyInfo(infos);
		for(Info ii : info) {
			System.out.println(ii.getInfo_id());
		}
	}*/
	
	/*@Test
	public void testFindAll(){
		List<User> users = userMapper.findAllUsers();
		for(User user : users){// 列表查询
			System.out.println(user);
		}
	}
	
	//根据用户的用户名来查询用户的信息
	@Test
	public void testFindByUsername(){
		List<User> users = userMapper.findByName("A");
		for(User user : users){// 列表查询
			System.out.println(user);
		}
	}
	
	@Test
	public void testFindByParams(){
		User userParam = new User();
		userParam.setUsername("A");
		userParam.setSex("F");
		List<User> users = userMapper.findByParams(userParam);
		for(User user : users){// 列表查询
			System.out.println(user);
		}
	}
	
	
	@Test
	public void testFindListByParams() throws Exception{
		UserParam userParam = new UserParam();
		userParam.setUsername_like("A");
		userParam.setSex_equal("F");
		
		//日期转换的工具类 SimpleDateFormat
		
		 * SimpleDateFormat 日期的date格式 与字符串之间的转换工具类
		 * 		两个核心方法：
		 * 			日期转换字符串： format(date)
		 * 			字符串转换日期：parse(String)
		 * 日期的格式： 
		 * 		年  y (四位的年 yyyy）
		 * 		月  M  
		 * 		日  d  
		 * 		时  H(24小时制) h(12小时制)
		 * 		分  m
		 * 		秒  s
		 * 		毫秒 S 
		 * 拼接日期 拼接符，可以任意定义 
		 
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		userParam.setBirthday_begin(sdf.parse("2015-06-01"));
		//查询总结果集
		List<User> users = userMapper.findListByParam(userParam);
		for(User user : users){// 列表查询
			System.out.println(user);
		}
		
		//查询总数据量
		int result = userMapper.findListByParamCount(userParam);
		System.out.println("result="+result);
	}
}*/
