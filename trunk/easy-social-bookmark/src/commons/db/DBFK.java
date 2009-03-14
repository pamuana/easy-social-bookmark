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

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Annotation para utilizar nos Model Beans. 
 * Usada para a cria��o de tabelas e consultas.
 * Define uma chave estrangeira.
 * 
 */

@Retention(value=RetentionPolicy.RUNTIME)
public @interface DBFK {
    String table();
}
