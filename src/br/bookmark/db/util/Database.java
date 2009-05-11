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
package br.bookmark.db.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

	private String dialect;
    private String driver;
    private String connectionURL;
    
    public Database() {
	
    }
    /**
     * Construtor da classe Database
     * 
     * @param dialect dialeto utilizado
     * @param driver driver do banco
     * @param connectionURL URL de conex�o
     * */
    public Database(String dialect, String driver, String connectionURL) {
    	this.dialect = dialect;
    	this.driver = driver;
    	this.connectionURL = connectionURL;
    }
    
    /**
     * Cria e retorna uma conex�o
     * 
     * @return Connection conex�o com o banco
     * 
     * */
    public Connection createConnection() throws Exception {
        Class.forName(this.driver);
        Connection dbCon = DriverManager.getConnection(this.connectionURL);
        return dbCon;
    }
    
    /**
     * Retorna o driver utilizado
     * 
     * @return String Retorna o driver 
     * 
     * */

    public String getDriver() {
        return this.driver;
    }
    
    /**
     * Atribui um driver ao Database
     * 
     * @param driver Driver a ser atribuido
     * 
     * */

    public void setDriver(String driver) {
        this.driver = driver;
    }
    
    /**
     * retorna uma string como a URL de conex�o
     * 
     * @return String retorna URL de conex�o
     * 
     * */

    public String getConnectionURL() {
        return this.connectionURL;
    }
    
    /**
     * Atribui uma url de conex�o ao Database
     * 
     * @param connectionURL URL de conex�o
     * */

    public void setConnectionURL(String connectionURL) {
        this.connectionURL = connectionURL;
    }
    
    /**
     * Retorna o dialeto utilizado no Database
     *@return String dialeto do banco
     * */

	public String getDialect() {
		return dialect;
	}

	/**
	 * Atribui um dialeto ao Database
	 * 
	 * @param dialect Dialeto do banco
	 * */
	public void setDialect(String dialect) {
		this.dialect = dialect;
	}
    
}
