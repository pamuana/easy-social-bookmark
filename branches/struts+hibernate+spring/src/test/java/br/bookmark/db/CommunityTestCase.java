package br.bookmark.db;

import br.bookmark.models.Community;
import br.bookmark.models.User;

public class CommunityTestCase extends PersistenceBaseClass {
	

	public void testCreateFind() throws Exception{
		
		Community community = new Community();
		community.setName("communityTest");
		community.setDescription("communityDescription");
				
		entityMgr.persist(community);
		entityMgr.flush();
		
		Community test = entityMgr.find(Community.class,community.getId());
		
		assertNotNull(test);
		assertEquals(community.getName(),test.getName());
		assertEquals(community.getDescription(),test.getDescription());
	}
	
	public void testCreateWithUserFind() throws Exception{
		
		User user01 = new User();
		user01.setName("userTestCollection01");
		user01.setEmail("userTestCollection01@email.com");
		user01.setLogin("userTestCollection01login");
		user01.setPassword("userTestCollection01password");
		entityMgr.persist(user01);
		entityMgr.flush();
		
		User user02 = new User();
		user02.setName("userTestCollection02");
		user02.setEmail("userTestCollection02@email.com");
		user02.setLogin("userTestCollection02login");
		user02.setPassword("userTestCollection02password");
		entityMgr.persist(user02);
		entityMgr.flush();
		
		Community community = new Community();
		community.setName("communityTestCoolection");
		community.setDescription("communityDescriptionCollection");
		
		community.addUser(user01);
		community.addUser(user02);
		
		entityMgr.persist(community);
		entityMgr.flush();
		
		Community test = entityMgr.find(Community.class,community.getId());
		
		assertNotNull(test);
		assertEquals(community.getName(),test.getName());
		assertEquals(community.getDescription(),test.getDescription());
		
		assertEquals(community.getUsers().get(0).getId(),test.getUsers().get(0).getId());
		assertEquals(community.getUsers().get(0).getEmail(),test.getUsers().get(0).getEmail());
		assertEquals(community.getUsers().get(0).getLogin(),test.getUsers().get(0).getLogin());
		assertEquals(community.getUsers().get(0).getPassword(),test.getUsers().get(0).getPassword());
		
		assertEquals(community.getUsers().get(1).getId(),test.getUsers().get(1).getId());
		assertEquals(community.getUsers().get(1).getEmail(),test.getUsers().get(1).getEmail());
		assertEquals(community.getUsers().get(1).getLogin(),test.getUsers().get(1).getLogin());
		assertEquals(community.getUsers().get(1).getPassword(),test.getUsers().get(1).getPassword());
		
	}
	
}
