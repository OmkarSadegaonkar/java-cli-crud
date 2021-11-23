package dao;
/*DAO- data access object = classes with methods will interact with the database */

import java.sql.*;
import java.util.*;
import dbutil.DBUtil;
import pojo.Person;

public class PeopleManagementDAO {

    //get all products method.  used List instead of ArrayList so it's better code management
    //incase we want to change productList ArrayList to a LinkedList, we don't have to change the jdbc code that much
    public List<Person> getAllPersons()
    {
        List<Person> productList = new ArrayList<Person>();
        try
        {
            //typical jdbc coding
            Connection conn = DBUtil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM persons");
            while(rs.next())
            {
                Person product = new Person(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"));
                productList.add(product);
            }
            DBUtil.closeConnection(conn);  //close connection
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return productList;
    }

    //different query
    public int getCount()
    {
        try
        {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM persons");
            ResultSet rs = ps.executeQuery();
            //iterate through result
            while(rs.next())
            {
                return rs.getInt(1);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public int addPerson(Person person)
    {
        //status displays 1 if successfully inserted data or error; successful or not
        int status = 0;
        try
        {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO persons (firstName, lastName) VALUES(?,?)");
            //set parameters of query here but using the values for the product object
            ps.setString(1, person.getfirstName());
            ps.setString(2, person.getLastName());
            status = ps.executeUpdate();  //if successful status should return 1
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }

    //updates a person already in the table
    public int updatePerson(Person person)
    {
        int status = 0;
        try
        {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE persons SET firstName=?, lastName=? WHERE id=?");
            //set parameters of query here but using the values for the person object
            ps.setString(1, person.getfirstName());
            ps.setString(2, person.getLastName());
            ps.setInt(3, person.getId());
            status = ps.executeUpdate();  //if successful status should return 1
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }

    //deltes person already in the table
    public int deletePerson(int personId)
    {
        int status = 0;
        try
        {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM persons where id = ?");
            //set parameters of query here but using the values for the person object
            ps.setInt(1, personId);
            status = ps.executeUpdate();  //if successful status should return 1

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }

}//class
