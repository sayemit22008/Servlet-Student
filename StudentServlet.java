package com.example.first_servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class StudentServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException
    {
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String action=request.getParameter("action");
        response.setContentType("text/html");

        PrintWriter out=response.getWriter();

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Students","Atif","arpita");

            if("Insert".equals(action))
            {
                PreparedStatement ps=conn.prepareStatement("INSERT INTO studentmgt(name,email) VALUES (?,?)");
                ps.setString(1,name);
                ps.setString(2,email);
                ps.executeUpdate();
                out.println("<p>Student Added Successfully</p>");
            }

            else if("View".equals(action))
            {
                Statement stmt=conn.createStatement();
                ResultSet rs=stmt.executeQuery("SELECT * FROM studentmgt");

                out.print("<h2>Student Lists are Displayed</h2>");
                out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Email</th></tr>");

                while (rs.next())
                {
                    out.println("<tr>");
                    out.println("<td>"+rs.getInt("id") + "</td>");
                    out.println("<td>"+rs.getString("name") + "</td>");
                    out.println("<td>"+rs.getString("email") + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            }

            else if ("Update".equals(action))
            {
                PreparedStatement ps=conn.prepareStatement("UPDATE studentmgt SET email=? WHERE name=?");
                ps.setString(1,email);
                ps.setString(2,name);
                int rows = ps.executeUpdate();
                out.println(rows >0?"<p>Updated Successfully</p>":"<p>Updating Student is not possible</p>");
            }

            else if ("Delete".equals(action)) {
                PreparedStatement ps=conn.prepareStatement("DELETE FROM studentmgt WHERE name=?");
                ps.setString(1, name);
                int rows=ps.executeUpdate();
                out.println(rows>0?"<p>Deleted Successfully</p>":"<p>No students are found to be deleted</p>");
            }

            conn.close();
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
}

