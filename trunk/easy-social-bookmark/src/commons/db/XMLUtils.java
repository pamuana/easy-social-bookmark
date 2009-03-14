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
import java.lang.reflect.Method;

import org.dom4j.Element;

/**
 * Utilit�rio para lidar com XML e bean que segue as annotations do banco. 
 *
 */
public class XMLUtils {

    public static boolean parseElement(Object obj, Element element) throws Exception {
        // Pega o valor do elemento no xml e atribui ao campo correspondente do objeto
        String elementName = element.getName();
        String elementValue = element.getTextTrim();
        
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(elementName)) {
                if (fields[i].getAnnotation(DBField.class) != null) {
                    // Campo do tipo BD
                    String tipoCampo = fields[i].getAnnotation(DBField.class).type().toUpperCase();
                    if (tipoCampo.startsWith("TEXT") || tipoCampo.startsWith("MEMO")) {
                        Class[] parametro = {String.class};
                        Method met = ResultSetUtils.getMetodoSet(obj, fields[i].getName(), parametro);
                        met.invoke(obj, elementValue);
                    } else if (tipoCampo.startsWith("LONG")) {
                        Class[] parametro = {Long.TYPE};
                        Method met = ResultSetUtils.getMetodoSet(obj, fields[i].getName(), parametro);
                        met.invoke(obj, Long.parseLong(elementValue));
                    } else {
                        throw new GenericDAOException("Tipo n�o previsto: "+tipoCampo);
                    }
                } else if (fields[i].getAnnotation(DBFK.class) != null) {
                    // Campo do tipo FK
                    Class[] parametro = {Long.TYPE};
                    Method met = ResultSetUtils.getMetodoSet(obj, fields[i].getName(), parametro);
                    met.invoke(obj, Long.parseLong(elementValue));

                } else {
                    // Campo nao tem anotacao - tenta como string
                    Class[] parametro = {String.class};
                    Method met = ResultSetUtils.getMetodoSet(obj, fields[i].getName(), parametro);
                    met.invoke(obj, elementValue);
                }
                return true;
            }
        }
        return false;
    }
}
