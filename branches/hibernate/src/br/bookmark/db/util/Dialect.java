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

import sun.security.action.GetLongAction;

public class Dialect {
	
	private String name;
	
	/**
	 * construto da classe Dialect
	 * */
	private Dialect() {
		super();
	}
	
	/**
	 * Retorna do nome do dialeto
	 * 
	 * @return String Nome do dialeto
	 * 
	 * */

	public String getName() {
		return name;
	}
	
	/**
	 * Atribui um nome ao dialeto
	 * 
	 * @param name Nome do dialeto
	 * */

	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Retorna o dialeto pelo nome
	 * 
	 * @param name Nome do dialeto
	 * @return Dialect Dialeto
	 * @throws Exception exce��o
	 * */

	public static Dialect getDialect(String name) throws Exception {
		Dialect dialect = new Dialect();
		dialect.setName(name);
		return dialect;
	}
	/**
	 * Retorna o nome do campo pelo tipo de campo
	 * 
	 * @param tipoCampo Tipo de campo
	 * @return String	Nome do campo
	 * @throws Exception GenericDAOException
	 */ 

	public String getFieldName(String tipoCampo) throws Exception {
        tipoCampo = tipoCampo.toUpperCase();
		if (tipoCampo.startsWith("TEXT") || tipoCampo.startsWith("VARCHAR")) {
        	return getTextName();
        } else if (tipoCampo.startsWith("MEMO")) {
        	return getMemoName(); 
        } else if (tipoCampo.startsWith("LONG") || tipoCampo.startsWith("BIGINT")) {
        	return getIntegerName(); 
        } else if (tipoCampo.startsWith("YESNO") || tipoCampo.startsWith("BOOL")) {
        	return getBooleanName();
        } else {
            throw new GenericDAOException("Tipo n�o previsto: "+tipoCampo);
        }
	}
	/**
	 * Retorna o nome inteiro do dialeto
	 * 
	 * @return String Nome do dialeto
	 * @throws GenericDAOException 
	 * */

	public String getIntegerName() throws Exception {
		if (this.name.equalsIgnoreCase("MSAccess")) {
			return "LONG";
		} else if (this.name.equalsIgnoreCase("MySQL")) {
			return "BIGINT";
		} else {
			throw new GenericDAOException("Dialeto n�o reconhecido");
		}
	}
	
	/**
	 * 	Retorna o nome do texto
	 * 
	 *  @return String Nome do texto do dialeto
	 *  @throws GenericDAOException
	 * */

	public String getTextName() throws Exception {
		if (this.name.equalsIgnoreCase("MSAccess")) {
			return "Text(255)"; // TODO: N�o ser fixo em 255
		} else if (this.name.equalsIgnoreCase("MySQL")) {
			return "VARCHAR(255)";
		} else {
			throw new GenericDAOException("Dialeto n�o reconhecido");
		}
	}
	
	/**
	 * Retorna o nome do Memo do dialeto
	 * 
	 * @return String Memo do dialeto
	 * @throws GenericDAOException
	 * */
	
	public String getMemoName() throws Exception {
		if (this.name.equalsIgnoreCase("MSAccess")) {
			return "MEMO";
		} else if (this.name.equalsIgnoreCase("MySQL")) {
			return "TEXT";
		} else {
			throw new GenericDAOException("Dialeto n�o reconhecido");
		}
	}
	
	/**
	 * Retorna o nome Boolean do dialeto
	 * 
	 * @return String Nome boolean
	 * @throws GenericDAOException 
	 * 
	 * */
	
	public String getBooleanName() throws Exception {
		if (this.name.equalsIgnoreCase("MSAccess")) {
			return "YESNO";
		} else if (this.name.equalsIgnoreCase("MySQL")) {
			return "BOOL";
		} else {
			throw new GenericDAOException("Dialeto n�o reconhecido");
		}
	}
	
	
}
