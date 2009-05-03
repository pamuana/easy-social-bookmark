package br.bookmark.db;

import br.bookmark.models.Community;
import br.bookmark.models.User;

public class UserTestCase extends PersistenceBaseClass {
	

	public void testCreateFind() throws Exception{
		
		User user = new User();
		user.setName("testUser");
		user.setEmail("testUser@mail.com");
		user.setLogin("testLogin");
		user.setPassword("testPassword");
		
		entityMgr.persist(user);
		entityMgr.flush();
		
		User test = entityMgr.find(User.class,user.getId());
		
		assertNotNull(test);
		assertEquals(user.getName(),test.getName());
		assertEquals(user.getEmail(),test.getEmail());
		assertEquals(user.getLogin(),test.getLogin());
		assertEquals(user.getPassword(),test.getPassword());
		
	}
	
	public void testCreateWithCommunityFind() throws Exception{
		
		Community community01 = new Community();
		community01.setName("communityName01");
		community01.setDescription("community01-Description");
		entityMgr.persist(community01);
		entityMgr.flush();
		
		Community community02 = new Community();
		community02.setName("communityName02");
		community02.setDescription("community02-Description");
		entityMgr.persist(community02);
		entityMgr.flush();
		
		Community community03 = new Community();
		community03.setName("communityName03");
		community03.setDescription("community03-Description");
		entityMgr.persist(community03);
		entityMgr.flush();
		
		User user = new User();
		user.setName("testUserCollection");
		user.setEmail("testUser@Collection.com");
		user.setLogin("testLoginCollecion");
		user.setPassword("testPasswordCollection");
		
		entityMgr.persist(user);
		entityMgr.flush();
		
		community01.addUser(user);
		entityMgr.persist(community01);
		entityMgr.flush();
		community02.addUser(user);
		entityMgr.persist(community02);
		entityMgr.flush();
		community03.addUser(user);
		entityMgr.persist(community03);
		entityMgr.flush();
		
		User test = entityMgr.find(User.class,user.getId());
		
		assertNotNull(test);
		assertEquals(user.getName(),test.getName());
		assertEquals(user.getEmail(),test.getEmail());
		assertEquals(user.getLogin(),test.getLogin());
		assertEquals(user.getPassword(),test.getPassword());
		
		Community testCommunity01 = entityMgr.find(Community.class,community01.getId());
		Community testCommunity02 = entityMgr.find(Community.class,community02.getId());
		Community testCommunity03 = entityMgr.find(Community.class,community03.getId());
		
		assertTrue(testCommunity01.getUsers().contains(test));
		assertTrue(testCommunity02.getUsers().contains(test));
		assertTrue(testCommunity03.getUsers().contains(test));
		
	}
	
	public void testValidaUser(){
		int i= entityMgr.createQuery("FROM User WHERE login = 'user' AND password='usuario' ").getResultList().size();
		System.out.print("number:"+i);
		assertTrue(true);
		String login="chalco"; String usuario="geiser";
		User user = (User) entityMgr.createQuery("FROM User WHERE login = '"+login+"' AND password='"+usuario+"' ").getSingleResult();
		System.out.print("name:"+user.getName());
		//User user = (User) entityMgr.createQuery("FROM User WHERE login = 'user' AND password='usuario' ")
		//return User.class.cast(entityMgr.createQuery("FROM User WHERE login = '"+login+"' AND password='"+password+"' ").getSingleResult());
	}
	
}
