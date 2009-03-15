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
package br.bookmark.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Funcionalidades para facilitar o acesso ao banco de dados.
 * 
 */
public class DataBaseUtils {

	/**
	 * Testa se uma tabela espec�fica existe no banco
	 * 
	 * @param dbCon conex�o do banco de dados
	 * @param tableName Nome da tabela
	 * @throws Exception exce��o
	 * 
	 * */
    public static boolean testIfTableExist(Connection dbCon, String tableName) throws Exception {
        DatabaseMetaData meta = dbCon.getMetaData();
        String[] types = {"TABLE"};
        ResultSet aRs = meta.getTables(null, null, tableName, types);
        if (aRs.next()) return true;
        return false;
    }
    
    /**
     * Deleta uma tabela espec�fica do banco
     * 
     *  @param dbCon conex�o do banco de dados
     *  @param tableName nome da tabela
     *  @throws Exception exce��o
     * 
     * */

    public static void drop(Connection dbCon, String tableName) throws Exception {
        if (testIfTableExist(dbCon, tableName)) {
            dbCon.createStatement().executeUpdate("DROP TABLE "+tableName);
        }
    }
    
    /**
     * Cria uma tabela no banco de dados
     * 
     * @param dbCon conex�o do banco de dados
     * @param tableName nome da tabela
     * @param fields campos da tabela
     * @throws Exception exce��o
     * 
     * */

    public static void createTable(Connection dbCon, String tableName, String fields) throws Exception {
        if ( !testIfTableExist(dbCon, tableName)) {
            Statement aStmt = dbCon.createStatement();
            String sql = "CREATE TABLE "+tableName + " " + fields;
            aStmt.execute(sql);
        }
    }
}
