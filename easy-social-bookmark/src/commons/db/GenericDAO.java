//  Copyright (C) Marco Aur�lio Gerosa All Rights Reserved.
// 	Copyright (C) Universidade de S�o Paulo All Rights Reserved.
//
//  This is free software; you can redistribute it and/or modify
//  it under the terms of the GNU General Public License as published by
//  the Free Software Foundation; either version 2 of the License, or
//  (at your option) any later version.
//
//  This software is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU General Public License for more details.
//
//  You should have received a copy of the GNU General Public License
//  along with this software; if not, write to the Free Software
//  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307,
//  USA.
//
package commons.db;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;



/**
 * Esta classe oferece diversas funcionalidades prontas para constru��o de 
 * classes do Padr�o Data Access Object (DAO) para acesso ao banco de dados. 
 * Deve ser herada pelos DAOs espec�ficos.
 */
public abstract class GenericDAO<T> {

    private Class<T> persistentClass;
    private String tableName = "";
    private String prefixoTabela = null;
    private long lastInstanceId = 0;
    private boolean init = false;
    private Database database;

    /**
     * construtor da classe GenericDAO 
     * @throws Exception exce��o
     * */
    public GenericDAO() throws Exception {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    /**
     * Retorna uma Conex�o 
     * @return Connection Retorna Conex�o
     * @throws Exception exce��o
     * */
    protected Connection createConnection() throws Exception  {
        return this.database.createConnection();
    }

    /**
     * Inicializa��o do GenericDAO
     * @param prefixoTabela prefixo da tabela a ser inicializado
     * @param database	banco de dados
     * @throws Exception exce��o
     * */
    public void init(String prefixoTabela, Database database) throws Exception {
    	this.database = database;
        Connection dbCon = createConnection();
        ResultSet aRs;
        // Nome da tabela
        this.tableName = persistentClass.getSimpleName();
        setPrefixoTabela(prefixoTabela);
        // Testa se a tabela existe
        if (!DataBaseUtils.testIfTableExist(dbCon, getTableName())) {
            createTable();
            dbCon = createConnection();
        }
        // LastId
        Statement aStmt = dbCon.createStatement();
        String str = "SELECT max(id) FROM "+getTableName();
        aRs = aStmt.executeQuery(str);
        aRs.next();
        lastInstanceId = aRs.getInt(1);
        dbCon.close();
        init = true;
    }
    
    /**
     * Retorna a classe de persist�ncia
     * @return Class<T> retona classe
     * */
    public Class<T> getPersistentClass() {
        return persistentClass;
    }
    /**
     * Retorna o nome da tabela 
     * @return String Retorna o nome da tabela 
     * */

    public String getTableName() {
        return this.prefixoTabela + this.tableName;
    }

    /**
     * Retorna prefixo da tabela
     * @return String Retorna prefixo da tabela
     * */
    public String getPrefixoTabela() {
        return this.prefixoTabela;
    }
    
    /**
     * Atribui o prefixo a uma tabela espac�fica
     * @param prefixoTabela prefixo da tabela
     * */

    public void setPrefixoTabela(String prefixoTabela) {
        if (prefixoTabela.equals("")) {
            this.prefixoTabela = "";
        } else {
            this.prefixoTabela = prefixoTabela.replace('.','_');
            this.prefixoTabela = this.prefixoTabela.replace('/','_');
            this.prefixoTabela = this.prefixoTabela.replace('-','_');
            this.prefixoTabela = this.prefixoTabela.replace('\\','_') + "_";
        }
    }
    /**
     * Retorna uma cole��o de elementos
     * @return Collection<T> Cole��o de elementos
     * @throws GenericDAOException
     * */
    
    public Collection<T> findAll() throws Exception {
        if (!this.init) throw new GenericDAOException ("DAO n�o inicializado.");
        Connection dbCon = createConnection();
        Statement aStmt = dbCon.createStatement();
        String str = "SELECT * FROM "+getTableName();
        ResultSet aRs = aStmt.executeQuery(str);
        T element = persistentClass.newInstance();
        Collection<T> result = ResultSetUtils.getCollection(element, aRs);
        dbCon.close();
        return result;
    }
    
    /**
     * Retorna um elemento pelo ID
     * @param id Id da objeto
     * @return T elemento
     * @throws GenericDAOException
     * */
    
    public T findById(long id) throws Exception {
        if (!this.init) throw new GenericDAOException ("DAO n�o inicializado.");
        Connection dbCon = createConnection();
        Statement aStmt = dbCon.createStatement();
        String str = "SELECT * FROM "+getTableName()+" WHERE id="+id;
        ResultSet aRs = aStmt.executeQuery(str);
        T element = persistentClass.newInstance();
        boolean found = ResultSetUtils.getElement(element, aRs);
        if (!found) element = null;
        dbCon.close();
        return element;
    }
    /**
     * Encontra um elemento pelo crit�rio
     * @param criterio Crit�rio de sele��o
     * @return T elemento 
     * @throws GenericDAOException
     * */
    
    protected T findElementByCriterio(String criterio) throws Exception {
        if (!this.init) throw new GenericDAOException ("DAO n�o inicializado.");
        Connection dbCon = createConnection();
        Statement aStmt = dbCon.createStatement();
        String str = "SELECT * FROM "+getTableName()+" WHERE "+criterio;
        //System.out.println(str);
        ResultSet aRs = aStmt.executeQuery(str);
        T element = persistentClass.newInstance();
        boolean found = ResultSetUtils.getElement(element, aRs);
        if (!found) element = null;
        dbCon.close();
        return element;
    }
    /**
     * Retorna uma cole��o de elementos pelo criterio
     * @param criterio Crit�rio de sele��o
     * @return Collection<T> cole��o de elementos
     * @throws GenericDAOException
     * */

    protected Collection<T> findCollectionByCriterio(String criterio) throws Exception {
        if (!this.init) throw new GenericDAOException ("DAO n�o inicializado.");
        Connection dbCon = createConnection();
        Statement aStmt = dbCon.createStatement();
        String str = "SELECT * FROM "+getTableName()+" WHERE "+criterio;
        //System.out.println(str);
        ResultSet aRs = aStmt.executeQuery(str);
        T element = persistentClass.newInstance();
        Collection<T> result = ResultSetUtils.getCollection(element, aRs);
        dbCon.close();
        return result;
    }

    /**
     * Retorna um campo pelo nome do campo, nome da tabela e crit�rio
     * @param fieldName nome do campo
     * @param tableName nome da tabela
     * @param criterio crit�rio de sele��o
     * @return Collection<String> cole��o de campos
     * @throws GenericDAOException
     * */
    protected Collection<String> findField(String fieldName, String tableName, String criterio) throws Exception {
        if (!this.init) throw new GenericDAOException ("DAO n�o inicializado.");
        Connection dbCon = createConnection();
        Statement aStmt = dbCon.createStatement();
        String str = "SELECT DISTINCT "+fieldName+" FROM "+tableName+(!criterio.equals("")?" WHERE "+criterio:"");
        ResultSet aRs = aStmt.executeQuery(str);
        Collection<String> result = new ArrayList<String>();
        while (aRs.next()) {
            result.add(aRs.getString(1));
        }
        dbCon.close();
        return result;
    }
    /**
     * Retorna o n�mero de elementos de uma tabela pelo crit�rio
     * @param tableName Nome da tabela
     * @param criterio Crit�rio de sele��o
     * @return long n�mero de elementos
     * @throws GenericDAOException
     * */
    
    protected long count(String tableName, String criterio) throws Exception {
        if (!this.init) throw new GenericDAOException ("DAO n�o inicializado.");
        Connection dbCon = createConnection();
        Statement aStmt = dbCon.createStatement();
        String str = "SELECT COUNT(*) FROM "+tableName+(!criterio.equals("")?" WHERE "+criterio:"");
        ResultSet aRs = aStmt.executeQuery(str);
        aRs.next();
        long result = aRs.getLong(1);
        dbCon.close();
        return result;
    }
    /**
     * Cria uma nova Tabela
     * @throws Exception
     * */
    
    protected void createTable() throws Exception {
        System.out.println("Criando tabela "+getTableName());
        Dialect dialect = Dialect.getDialect(database.getDialect());
        Field[] fields = this.persistentClass.getDeclaredFields();
        StringBuffer str = new StringBuffer("CREATE TABLE "+getTableName()+" (");
        StringBuffer strFK = new StringBuffer();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getAnnotation(DBField.class) != null) {
                str.append(fields[i].getName() + " " + dialect.getFieldName(fields[i].getAnnotation(DBField.class).type())+", ");
            } else if (fields[i].getAnnotation(DBFK.class) != null) {
                str.append(fields[i].getName() + " "+dialect.getIntegerName()+", ");
                if (this.database.getDialect().equals("MSAccess")) {
                	strFK.append(", CONSTRAINT FK"+fields[i].getName()+" FOREIGN KEY("+fields[i].getName()+") REFERENCES "+this.prefixoTabela+fields[i].getAnnotation(DBFK.class).table());
                	// TODO - fazer funcionar no MySQL
                }
            }
        }
        str.append("CONSTRAINT PrimaryKey PRIMARY KEY (id)");
        str.append(strFK.toString());
        str.append(")");
        String sql = str.toString();
        Connection dbCon = createConnection();
        Statement aStmt = dbCon.createStatement();
        //System.out.println(sql);
        aStmt.executeUpdate(sql);
        //dbCon.commit(); 
        dbCon.close();
        createAdditionalTables();
    }
    
    /**
     * M�todo abstrato para criar tabelas adicionais 
     * */
    protected abstract void createAdditionalTables() throws Exception;
    
    /**
     * Deleta uma tabela
     * 
     * */
    
    public void dropTable() throws Exception {
        if (!this.init) throw new GenericDAOException ("DAO n�o inicializado.");
        Connection dbCon = createConnection();
        Statement aStmt = dbCon.createStatement();
        String str = "DROP TABLE "+getTableName();
        aStmt.executeUpdate(str);
        dbCon.close();
    }
    
    /**
     * Salva um objeto no banco
     * @param obj objeto a ser salvo
     * @throws GenericDAOException
     * */
    public void save(T obj) throws Exception {
        if (!this.init) throw new GenericDAOException ("DAO n�o inicializado.");
        long id = ResultSetUtils.getId(obj).longValue();
        Connection dbCon = createConnection();
        Field[] fields = this.persistentClass.getDeclaredFields();
        if (id == 0) {
            // *** INSERT ***
            // Atualiza id
            this.lastInstanceId++;
            Class[] parametro = {Long.TYPE};
            ResultSetUtils.getMetodoSet(obj, "id", parametro).invoke(obj, this.lastInstanceId);
            // Campos
            StringBuffer str = new StringBuffer("INSERT INTO "+getTableName()+"(");
            int count = 0;
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getAnnotation(DBField.class) != null || 
                        fields[i].getAnnotation(DBFK.class) != null) {
                    str.append(fields[i].getName() + ",");
                    count++;
                }
            }
            // Tira a ultima virgula
            str = new StringBuffer(str.substring(0, str.length()-1));
            // Valores
            str.append(") VALUES (");
            for (int i = 0; i < count; i++) {
                str.append("?,");
            }
            str = new StringBuffer(str.substring(0, str.length()-1)); // Tira a ultima virgula
            str.append(")");
            PreparedStatement aStmt = dbCon.prepareStatement(str.toString());
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getAnnotation(DBField.class) != null) {
                    String tipoCampo = fields[i].getAnnotation(DBField.class).type().toUpperCase();
                    if (tipoCampo.startsWith("TEXT") || tipoCampo.startsWith("VARCHAR") || tipoCampo.startsWith("MEMO")) {
                        String valor = (String) ResultSetUtils.getMetodoGet(obj, fields[i].getName()).invoke(obj);
                        aStmt.setString(i+1, valor);
                    } else if (tipoCampo.startsWith("LONG") || tipoCampo.startsWith("BIGINT")) {
                        Long valor = (Long) ResultSetUtils.getMetodoGet(obj, fields[i].getName()).invoke(obj);
                        aStmt.setInt(i+1, (int) valor.longValue());  // OBS: Com long estava dando erro no Access                      
                    }
                    else if (tipoCampo.startsWith("YESNO") || tipoCampo.startsWith("BOOL")) {
                        Boolean valor = (Boolean) ResultSetUtils.getMetodoGet(obj, fields[i].getName()).invoke(obj);
                        aStmt.setBoolean(i+1, (boolean) valor.booleanValue());                      
                    }
                    else {
                        throw new GenericDAOException("Tipo n�o previsto: "+tipoCampo);
                    }
                /*} else if (fields[i].getAnnotation(DBFK.class) != null) {
                    Object obj2 = ResultSetUtils.getMetodoGet(obj, fields[i].getName()).invoke(obj);
                    Long valor = ResultSetUtils.getId(obj2);
                    aStmt.setInt(i+1, (int) valor.longValue());  // OBS: Com long estava dando erro no Access                      
                }*/
                } else if (fields[i].getAnnotation(DBFK.class) != null) {
                    Long valor = (Long) ResultSetUtils.getMetodoGet(obj, fields[i].getName()).invoke(obj);
                    if (valor.longValue() != 0) {
                        aStmt.setInt(i+1, (int) valor.longValue());  // OBS: Com long estava dando erro no Access
                    } else {
                        aStmt.setString(i+1, null); 
                    }
                }
            }            
            // Executa Query
            aStmt.executeUpdate();
            
        } else {
            // UPDATE
            // Campos
            StringBuffer str = new StringBuffer("UPDATE "+getTableName()+" SET ");
            int count = 0;
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getAnnotation(DBField.class) != null || 
                        fields[i].getAnnotation(DBFK.class) != null) {
                    str.append(fields[i].getName() + " = ?,");
                    count++;
                }
            }
            // Tira a ultima virgula
            str = new StringBuffer(str.substring(0, str.length()-1));
            str.append(" WHERE id="+id);
            PreparedStatement aStmt = dbCon.prepareStatement(str.toString());
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getAnnotation(DBField.class) != null) {
                    String tipoCampo = fields[i].getAnnotation(DBField.class).type().toUpperCase();
                    if (tipoCampo.startsWith("TEXT") || tipoCampo.startsWith("VARCHAR") || tipoCampo.startsWith("MEMO")) {
                        String valor = (String) ResultSetUtils.getMetodoGet(obj, fields[i].getName()).invoke(obj);
                        aStmt.setString(i+1, valor);
                    } else if (tipoCampo.startsWith("LONG") || tipoCampo.startsWith("BIGINT")) {
                        Long valor = (Long) ResultSetUtils.getMetodoGet(obj, fields[i].getName()).invoke(obj);
                        aStmt.setInt(i+1, (int) valor.longValue());  // OBS: Com long estava dando erro no Access                      
                    } 
                    
                    else if (tipoCampo.startsWith("YESNO") || tipoCampo.startsWith("BOOL")) {
                        Boolean valor = (Boolean) ResultSetUtils.getMetodoGet(obj, fields[i].getName()).invoke(obj);
                        aStmt.setBoolean(i+1, (boolean) valor.booleanValue());                      
                    }
                    
                    else {
                        throw new GenericDAOException("Tipo n�o previsto: "+tipoCampo);
                    }
                /*} else if (fields[i].getAnnotation(DBFK.class) != null) {
                    Object obj2 = ResultSetUtils.getMetodoGet(obj, fields[i].getName()).invoke(obj);
                    Long valor = ResultSetUtils.getId(obj2);
                    aStmt.setInt(i+1, (int) valor.longValue());  // OBS: Com long estava dando erro no Access                      
                }*/
                } else if (fields[i].getAnnotation(DBFK.class) != null) {
                    Long valor = (Long) ResultSetUtils.getMetodoGet(obj, fields[i].getName()).invoke(obj);
                    if (valor.longValue() != 0) {
                        aStmt.setInt(i+1, (int) valor.longValue());  // OBS: Com long estava dando erro no Access
                    } else {
                        aStmt.setString(i+1, null); 
                    }
                }
            }            
            // Executa Query
            aStmt.executeUpdate();
        }
        //dbCon.commit(); 
        dbCon.close();
    }
    
    /**
     * Deleta um objeto do banco
     * @param obj objeto a ser deletado
     * @throws GenericDAOException
     * */

    public void delete(T obj) throws Exception {
        if (!this.init) throw new GenericDAOException ("DAO n�o inicializado.");
        long id = ResultSetUtils.getId(obj).longValue();
        Connection dbCon = createConnection();
        dbCon.createStatement().executeUpdate("DELETE FROM "+getTableName()+" WHERE id="+id);
        //dbCon.commit(); 
        dbCon.close();
    }
    /**
     * Deleta um elemento pelo id
     * @param id Id do elemento
     * @throws GenericDAOException
     * */
    
    public void delete(long id) throws Exception {
        if (!this.init) throw new GenericDAOException ("DAO n�o inicializado.");
        Connection dbCon = createConnection();
        dbCon.createStatement().executeUpdate("DELETE FROM "+getTableName()+" WHERE id="+id);
        //dbCon.commit(); 
        dbCon.close();
    }      
    /**
     * Deleta um elemento pelo nome do campo e pelo valor
     * @param fieldName nome do campo
     * @param value valor
     * @throws GenericDAOException
     * */
    
    public void delete(String fieldName, long value) throws Exception {
        if (!this.init) throw new GenericDAOException ("DAO n�o inicializado.");
        Connection dbCon = createConnection();
        dbCon.createStatement().executeUpdate("DELETE FROM "+getTableName()+" WHERE "+fieldName+"="+value);
        //dbCon.commit(); 
        dbCon.close();
    }
    
    /**
     * Encontra um campo sem distin��o, pelo nome do Campo, nome da tabela e criterio
     * @param fieldName nome do campo
     * @param tableName nome da tabela
     * @param criterio crit�rio de sele��o
     * @return Collection<String> cole��o de campos
     * @throws GenericDAOException
     * */
    
    //TODO Additional Method to recover information with out Distinct of log -- Needed in findTaskIdsFromLog
    protected Collection<String> findFieldWithOutDistinct(String fieldName, String tableName, String criterio) throws Exception {
        if (!this.init) throw new GenericDAOException ("DAO n�o inicializado.");
        Connection dbCon = createConnection();
        Statement aStmt = dbCon.createStatement();
        String str = "SELECT "+fieldName+" FROM "+tableName+(!criterio.equals("")?" WHERE "+criterio:"");
        //System.out.println(str);
        ResultSet aRs = aStmt.executeQuery(str);
        Collection<String> result = new ArrayList<String>();
        while (aRs.next()) {
            result.add(aRs.getString(1));
        }
        dbCon.close();
        return result;
    }
    /**
     * Encontra campos pelo jun��o � esquerda utilizando nome do campo, potteryTableName, s�tio do nome da tabela, regra e crit�rio
     * @param fieldName nome do campo
     * @param potteryTableName 
     * @param siteTableName
     * @param rule
     * @param criterio
     * @return Collection<String>
     * @throws GenericDAOException
     * */
    
    //TODO Additional Method to recover information of log  
    protected Collection<String> findFieldLeftJoin(String fieldName, String potteryTableName, String siteTableName, String rule, String criterio) throws Exception {
        if (!this.init) throw new GenericDAOException ("DAO n�o inicializado.");
        Connection dbCon = createConnection();
        Statement aStmt = dbCon.createStatement();
        String str = "SELECT "+fieldName+" FROM "+potteryTableName+" LEFT JOIN "+siteTableName+" ON "+rule+(!criterio.equals("")?" WHERE "+criterio:"");
        //System.out.println(str);
        ResultSet aRs = aStmt.executeQuery(str);
        Collection<String> result = new ArrayList<String>();
        while (aRs.next()) {
            result.add(aRs.getString(1));
        }
        dbCon.close();
        return result;
    }
}
