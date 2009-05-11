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

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

 /**
  * Utilit�rios para lidar com ResultSets.
  *  
  */
 public class ResultSetUtils {


     /**
      * Returns next record of result set as a Map.
      * The keys of the map are the column names,
      * as returned by the metadata.
      * The values are the columns as Objects.
      *
      * @param resultSet The ResultSet to process.
      * @exception SQLException if an error occurs.
      */
     public static Map getMap(ResultSet resultSet)
        throws SQLException {

            // Acquire resultSet MetaData
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols = metaData.getColumnCount();

            // Create hashmap, sized to number of columns
        HashMap row = new HashMap(cols,1);

            // Transfer record into hashmap
        if (resultSet.next()) {
            for (int i=1; i<=cols ; i++) {
                row.put(metaData.getColumnName(i),
                    resultSet.getObject(i));
            }
        } // end while

        return ((Map) row);

     } // end getMap


     /**
      * Return a Collection of Maps, each representing
      * a row from the ResultSet.
      * The keys of the map are the column names,
      * as returned by the metadata.
      * The values are the columns as Objects.
      *
      * @param resultSet The ResultSet to process.
      * @exception SQLException if an error occurs.
      */
     public static Collection getMaps(ResultSet resultSet)
        throws SQLException {

            // Acquire resultSet MetaData
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols = metaData.getColumnCount();

            // Use ArrayList to maintain ResultSet sequence
        ArrayList list = new ArrayList();

            // Scroll to each record, make map of row, add to list
        while (resultSet.next()) {
            HashMap row = new HashMap(cols,1);
            for (int i=1; i<=cols ; i++) {
                row.put(metaData.getColumnName(i),
                    resultSet.getString(i));
            }
            list.add(row);
        } // end while

        return ((Collection) list);

     } // end getMaps


    /**
     * Populate the JavaBean properties of the specified bean, based on
     * the specified name/value pairs.  This method uses Java reflection APIs
     * to identify corresponding "property setter" method names. The type of
     * the value in the Map must match the setter type. The setter must
     * expect a single arguement (the one on the Map).
     * <p>
     * The particular setter method to be called for each property is
     * determined using the usual JavaBeans introspection mechanisms. Thus,
     * you may identify custom setter methods using a BeanInfo class that is
     * associated with the class of the bean itself. If no such BeanInfo
     * class is available, the standard method name conversion ("set" plus
     * the capitalized name of the property in question) is used.
     * <p>
     * <strong>NOTE</strong>:  It is contrary to the JavaBeans Specification
     * to have more than one setter method (with different argument
     * signatures) for the same property.
     * <p>
     * This method adopted from the Jakarta Commons BeanUtils.populate.
     *
     * @author Craig R. McClanahan
     * @author Ralph Schaer
     * @author Chris Audley
     * @author Rey Fran�ois
     * @author Gregor Ra�man
     * @author Ted Husted
     *
     * @param bean JavaBean whose properties are being populated
     * @param properties Map keyed by property name, with the
     *  corresponding value to be set
     *
     * @exception IllegalAccessException if the caller does not have
     *  access to the property accessor method
     * @exception InvocationTargetException if the property accessor method
     *  throws an exception
     * @deprecated Use BeanUtils.CopyProperties instead.
     */
    public static void setProperties(Object bean, Map properties)
        throws IllegalAccessException, InvocationTargetException {

        if ((bean == null) || (properties == null))
            return;

        /*
        if (debug >= 1)
            System.out.println("BeanUtils.populate(" + bean + ", " +
                               properties + ")");
        */

        // Loop through the property name/value pairs to be set
        Iterator names = properties.keySet().iterator();
        while (names.hasNext()) {

            // Identify the property name and value(s) to be assigned
            String name = (String) names.next();
            if (name == null)
                continue;

            // Get the property descriptor of the requested property (if any)
            PropertyDescriptor descriptor = null;
            try {
                descriptor = PropertyUtils.getPropertyDescriptor(bean, name);
            } catch (Throwable t) {
                /*
                if (debug >= 1)
                    System.out.println("    getPropertyDescriptor: " + t);
                */
                descriptor = null;
            }
            if (descriptor == null) {
                /*
                if (debug >= 1)
                    System.out.println("    No such property, skipping");
                */
                continue;
            }

            /*
            if (debug >= 1)
                System.out.println("    Property descriptor is '" +
                                   descriptor + "'");
            */

            // Identify the relevant setter method (if there is one)
            Method setter = descriptor.getWriteMethod();
            if (setter == null) {
                /*
                if (debug >= 1)
                    System.out.println("    No setter method, skipping");
                */
                continue;
            }

            // Obtain value to be set
            Object[] args = new Object[1];
            args[0] = properties.get(name); // This MUST match setter type

            /*
            if (debug >= 1)
                System.out.println("  name='" + name + "', value.class='" +
                                   (value == null ? "NONE" :
                                   value.getClass().getName()) + "'");
            */
            /*
            if (debug >= 1)
                System.out.println("    Setting to " +
                                   (parameters[0] == null ? "NULL" :
                                    "'" + parameters[0] + "'"));
            */

            // Invoke the setter method
            setter.invoke(bean,args);
        }

        /*
        if (debug >= 1)
            System.out.println("============================================");
        */

    } // end setProperties


    /**
     * Map JDBC objects to Java equivalents.
     * Used by getBean() and getBeans().
     * <p>
     * Some types not supported.
     * Many not work with all drivers.
     * <p>
     * Makes binary conversions of BIGINT, DATE, DECIMAL, DOUBLE, FLOAT, INTEGER,
     * REAL, SMALLINT, TIME, TIMESTAMP, TINYINT.
     * Makes Sting conversions of CHAR, CLOB, VARCHAR, LONGVARCHAR, BLOB, LONGVARBINARY,
     * VARBINARY.
     * <p>
     * DECIMAL, INTEGER, SMALLINT, TIMESTAMP, CHAR, VARCHAR tested with MySQL and Poolman.
     * Others not guaranteed.
     * @param classeDestino 
     * @throws NoSuchFieldException 
     * @throws SecurityException 
     */
    private static void putEntry(
            Map properties,
            ResultSetMetaData metaData,
            ResultSet resultSet,
            int i, Class classeDestino)
        throws Exception {

        /*
        In a perfect universe, this would be enough
            properties.put(
                metaData.getColumnName(i),
                resultSet.getObject(i));
        But only String, Timestamp, and Integer seem to get through that way.
        */

        String columnName = metaData.getColumnName(i);
        
        // Testa se � uma FK
        /*Field[] fields = classeDestino.getDeclaredFields();
        for (int j = 0; j < fields.length; j++) {
            if (fields[j].getAnnotation(DBFK.class) != null) {
                properties.put(columnName, resultSet.getString(i));
            }
        }*/
//System.out.println(i+"-"+metaData.getColumnType(i));
        switch (metaData.getColumnType(i)) {

            // http://java.sun.com/j2se/1.3.0/docs/api/java/sql/Types.html

            case Types.BIGINT:
                properties.put(columnName,
                    new Long(resultSet.getLong(i)));
                break;

            case Types.DATE:
                properties.put(columnName,
                    resultSet.getDate(i));
                break;

            case Types.DECIMAL:
            case Types.DOUBLE:
                properties.put(columnName,
                    new Double(resultSet.getDouble(i)));
                break;

            case Types.FLOAT:
                properties.put(columnName,
                    new Float(resultSet.getFloat(i)));
                break;

            case Types.INTEGER:
                int valor = 0;
                try { // Se o campo esta vazio d� erro
                    valor = resultSet.getInt(i);
                } catch (SQLException e) {}
                properties.put(columnName, new Integer(valor));
                break;
                  
            case Types.REAL:
                properties.put(columnName,
                    new Double(resultSet.getString(i)));
                break;

            case Types.SMALLINT:
                properties.put(columnName,
                    new Short(resultSet.getShort(i)));
                break;

            case Types.TIME:
                properties.put(columnName,
                    resultSet.getTime(i));
                break;

            case Types.TIMESTAMP:
                properties.put(columnName,
                    resultSet.getTimestamp(i));
                break;

            // :FIXME: Throws java.lang.ClassCastException: java.lang.Integer
            // :FIXME: with Poolman and MySQL unless use getString.
            case Types.TINYINT:
                properties.put(columnName,
                    new Byte(resultSet.getString(i)));
                break;

            case Types.CHAR:
            case Types.CLOB:
            case Types.VARCHAR:
            case Types.LONGVARCHAR:
                // :FIXME: Handle binaries differently?
            case Types.BLOB:
            case Types.LONGVARBINARY:
            case Types.VARBINARY:
                properties.put(columnName,
                    resultSet.getString(i));
               break;

            /*
                :FIXME: Add handlers for
                ARRAY
                BINARY
                BIT
                DISTINCT
                JAVA_OBJECT
                NULL
                NUMERIC
                OTHER
                REF
                STRUCT
            */

             // Otherwise, pass as *String property to be converted
            default:
                properties.put(columnName + "String",
                    resultSet.getString(i));
                break;
        } // end switch

    } // end putEntry


    /**
     * Populate target bean with the first record from a ResultSet.
     *
     * @param resultSet The ResultSet whose parameters are to be used
     * to populate bean properties
     * @param target An instance of the bean to populate
     * @exception SQLException if an exception is thrown while setting
     * property values, populating the bean, or accessing the ResultSet
     * @return True if resultSet contained a next element
     */
    public static boolean getElement(Object target, ResultSet resultSet)
        throws Exception {

            // Check prerequisites
        if ((target==null) || (resultSet==null))
            throw new SQLException("getElement: Null parameter");

            // Acquire resultSet MetaData
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols = metaData.getColumnCount();

            // Create hashmap, sized to number of columns
        HashMap properties = new HashMap(cols,1);

            // Scroll to next record and pump into hashmap
        boolean found = false;
        if (resultSet.next()) {
            found = true;
            for (int i=1; i<=cols ; i++) {
                putEntry(properties,metaData,resultSet,i,target.getClass());
            }
           // try {
                BeanUtils.copyProperties(target,properties);
                //analyseRelationships(target);
          //  }
           // catch (Throwable t) {
               // throw new SQLException("ResultSetUtils.getElement: " +
               //     t.getMessage() + " - " + properties.toString());
            //}

        } // end if

        return found;

     } // end getElement




    /**
     * Return a ArrayList of beans populated from a ResultSet.
     *
     * @param resultSet The ResultSet whose parameters are to be used
     * to populate bean properties
     * @param target An instance of the bean to populate
     * @exception SQLException if an exception is thrown while setting
     * property values, populating the bean, or accessing the ResultSet
     */
     public static Collection getCollection(Object target, ResultSet resultSet) throws Exception {

            // Check prerequisites
        if ((target==null) || (resultSet==null))
            throw new GenericDAOException("getCollection: Null parameter");

            // Acquire resultSet MetaData
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols = metaData.getColumnCount();

            // Create hashmap, sized to number of columns
        HashMap properties = new HashMap(cols,1);

            // Use ArrayList to maintain ResultSet sequence
        ArrayList list = new ArrayList();

            // Acquire target class
        Class factory = target.getClass();

            // Scroll to next record and pump into hashmap
        while (resultSet.next()) {
            for (int i=1; i<=cols ; i++) {
                putEntry(properties,metaData,resultSet,i,target.getClass());
            }
            Object bean = factory.newInstance();
            BeanUtils.copyProperties(bean,properties);
            list.add(bean);
            properties.clear();

        } // end while

        return ((Collection) list);

     } // end getCollection
     
     
     private static void analyseRelationships(Object target) throws Exception {
         /*// - N�o � usado mais visto que os relacionamento passaram a ser por ids
         Field[] fields = target.getClass().getDeclaredFields();
         for (int i = 0; i < fields.length; i++) {
             if (fields[i].getAnnotation(DBRelationship.class) != null) {
                 Class classe = fields[i].getAnnotation(DBRelationship.class).classe();
                 Object target2 = classe.newInstance();
                 // Transfere objetos
                 Connection dbCon = DataBase.createConnection();
                 Statement aStmt = dbCon.createStatement();
                 String campos = "";
                 Field[] fields2 = target2.getClass().getDeclaredFields();
                 for (int j = 0; i < fields2.length; i++) {
                     if (fields[j].getAnnotation(DBField.class) != null) {
                         campos += fields2[j].getName() + ",";
                     }
                 }
                 campos = campos.substring(0, campos.length() -1); // tira a ultima virgula
                 // TODO - prefixo
                 ResultSet aRs = aStmt.executeQuery("SELECT "+campos+" FROM "+target2.getClass().getSimpleName()+" WHERE id="+getId(target));
                 if (fields[i].getType().getName().equals("java.util.Collection")) {
                     Collection<Object> collection = getCollection(target2, aRs);
                     Class[] parametro = {collection.getClass()};
                     Method metodoSet = getMetodoSet(target, fields[i].getName(), parametro);
                     metodoSet.invoke(target, collection);
                 } else {
                     getElement(target2, aRs);
                     Class[] parametro = {classe};
                     Method metodoSet = getMetodoSet(target, fields[i].getName(), parametro);
                     metodoSet.invoke(target, target2);
                 }
                 
             } else if (fields[i].getAnnotation(DBFK.class) != null) {
                 throw new GenericDAOException("TODO");
             }
         }
         */
     }
     
     public static Method getMetodoSet(Object target, String fieldName, Class[] parametro) throws Exception {
         return target.getClass().getMethod("set"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1), parametro);
     }

     public static Method getMetodoGet(Object target, String fieldName) throws Exception {
         return target.getClass().getMethod("get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1), null);
     }

     public static Long getId(Object target) throws Exception {
         return (Long) target.getClass().getMethod("getId", null).invoke(target, null);
     }

} // end ResultSetUtils
