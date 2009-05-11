
package br.bookmark.db;

import junit.framework.TestCase;

import javax.persistence.EntityTransaction;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceBaseClass extends TestCase {

    private EntityManagerFactory emf;
    protected EntityManager entityMgr;
    protected EntityTransaction tx;

    public PersistenceBaseClass() {
        super();
        emf = Persistence.createEntityManagerFactory("bookmarks");
    }

    public void testVoid(){
    	assertTrue(true);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        entityMgr = emf.createEntityManager();
        tx = entityMgr.getTransaction();
        tx.begin();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        tx.rollback();
    }

}